// front/src/components/client/ClientList.js
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Sidebar from '../../components/sidebar/Sidebar';
import Modal from 'react-modal';
import { getAllClients, deleteClient } from '../../service/ClientService';
import './ClientList.css';

Modal.setAppElement('#root'); // Para acessibilidade

const ClientList = () => {
    const [clients, setClients] = useState([]);
    const [filter, setFilter] = useState('');
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalMessage, setModalMessage] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchClients = async () => {
            try {
                const clientsData = await getAllClients();
                setClients(clientsData);
            } catch (error) {
                setModalMessage('Erro ao carregar os clientes.');
                setIsModalOpen(true);
            }
        };

        fetchClients();
    }, []);

    // Filtra os clientes com base no nome
    const filteredClients = clients.filter(client =>
        client.user.name.toLowerCase().includes(filter.toLowerCase())
    );

    const handleDelete = async (id) => {
        if (window.confirm('Tem certeza que deseja excluir este cliente?')) {
            try {
                await deleteClient(id);
                setClients(clients.filter(client => client.idClient !== id)); // Ajuste conforme o identificador correto
                setModalMessage('Cliente excluído com sucesso!');
                setIsModalOpen(true);
            } catch (error) {
                setModalMessage('Não é possível excluir o cliente no momento.');
                setIsModalOpen(true);
            }
        }
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    const handleGoHome = () => {
        navigate('/home');
    };

    return (
        <div className="client-list-page">
            <Sidebar />
            <div className="client-list-container">
                <div className="header">
                    <div className="header-buttons">
                        <button className="btn-back" onClick={handleGoHome}>Voltar</button>
                        <div className="search-bar">
                            <input
                                type="text"
                                placeholder="Filtrar por nome"
                                value={filter}
                                onChange={(e) => setFilter(e.target.value)}
                            />
                        </div>
                        <Link to="/clients/novo">
                            <button className="btn-primary">Criar Novo Cliente</button>
                        </Link>
                    </div>
                </div>

                <div className="client-cards">
                    {filteredClients.map(client => (
                        <div className="client-card" key={client.idClient}>
                            <h3>{client.user.name}</h3>
                            <p><strong>Email:</strong> {client.user.email}</p>
                            <p><strong>Cidade:</strong> {client.city.name}</p>
                            <div className="actions">
                                <Link to={`/clients/editar/${client.idClient}`}>
                                    <button className="btn-edit">Editar</button>
                                </Link>
                                <button className="btn-delete" onClick={() => handleDelete(client.user.idUser)}>Excluir</button>
                            </div>
                        </div>
                    ))}
                </div>

                {/* Modal para mensagens */}
                <Modal
                    isOpen={isModalOpen}
                    onRequestClose={closeModal}
                    className="modal"
                    overlayClassName="overlay"
                >
                    <div className="modal-content">
                        <p>{modalMessage}</p>
                        <button onClick={closeModal} className="btn-primary">Fechar</button>
                    </div>
                </Modal>
            </div>
        </div>
    );
};

export default ClientList;
