// front/src/components/client/ClientForm.js
import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getClientById, addClient, updateClient } from '../../service/ClientService';
import Sidebar from '../../components/sidebar/Sidebar';
import Modal from 'react-modal';
import './ClientForm.css';

Modal.setAppElement('#root'); // Para acessibilidade

const ClientForm = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [client, setClient] = useState({
        user: {
            name: '',
            email: ''
        },
        city: {
            name: '',
        }
    });
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [modalMessage, setModalMessage] = useState('');

    useEffect(() => {
        if (id) {
            const fetchClient = async () => {
                try {
                    const clientData = await getClientById(id);
                    setClient({
                        user: {
                            name: clientData.user.name,
                            email: clientData.user.email
                        },
                        city: {
                            name: clientData.city.name,
                        }
                    });
                } catch (error) {
                    if (error.response && error.response.status === 404) {
                        setModalMessage('Cliente não encontrado!');
                        setShowModal(true);
                        navigate('/clients');
                    } else {
                        setModalMessage('Erro ao carregar cliente.');
                        setShowModal(true);
                    }
                }
            };
            fetchClient();
        }
    }, [id, navigate]);

    const handleChange = (e) => {
        const { name, value } = e.target;

        // Determinar a qual parte do estado pertence o campo
        if (name === 'name' || name === 'email') {
            setClient((prevClient) => ({
                ...prevClient,
                user: {
                    ...prevClient.user,
                    [name]: value
                }
            }));
        } else if (name === 'cityName' || name === 'cityState') {
            setClient((prevClient) => ({
                ...prevClient,
                city: {
                    ...prevClient.city,
                    [name.replace('city', '').toLowerCase()]: value
                }
            }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);

        try {
            if (id) {
                await updateClient(id, client);
                setModalMessage('Cliente atualizado com sucesso!');
            } else {
                await addClient(client);
                setModalMessage('Cliente criado com sucesso!');
                // Redireciona para a lista de clientes após criar apenas após fechar o modal
            }
            setShowModal(true); // Exibe o modal após sucesso
        } catch (error) {
            console.error(error);
            setModalMessage('Erro ao salvar cliente. Tente novamente.');
            setShowModal(true);
        } finally {
            setIsSubmitting(false);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        navigate('/clients');
    };

    const handleGoBack = () => {
        navigate('/clients');
    };

    return (
        <div className="client-form-page">
            <Sidebar />
            <div className="client-form-container">
                <button className="btn-back" onClick={handleGoBack}>Voltar</button>
                <h2>{id ? 'Editar Cliente' : 'Adicionar Cliente'}</h2>
                <form onSubmit={handleSubmit} className="client-form">
                    <div className="form-group">
                        <label>Nome:</label>
                        <input
                            type="text"
                            name="name"
                            value={client.user.name}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Email:</label>
                        <input
                            type="email"
                            name="email"
                            value={client.user.email}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Cidade:</label>
                        <input
                            type="text"
                            name="cityName"
                            value={client.city.name}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Estado:</label>
                        <input
                            type="text"
                            name="cityState"
                            value={client.city.state}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group" style={{ width: '100%' }}>
                        <button type="submit" className="btn-primary" disabled={isSubmitting}>
                            {isSubmitting ? 'Salvando...' : (id ? 'Atualizar Cliente' : 'Adicionar Cliente')}
                        </button>
                    </div>
                </form>

                <Modal
                    isOpen={showModal}
                    onRequestClose={handleCloseModal}
                    className="modal-container"
                    overlayClassName="modal-overlay"
                >
                    <div className="modal-content">
                        <h3>{modalMessage}</h3>
                        <button onClick={handleCloseModal} className="btn-close">Fechar</button>
                    </div>
                </Modal>
            </div>
        </div>
    );
};

export default ClientForm;
