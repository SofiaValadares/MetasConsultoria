import './Footer.css';

function Footer() {
    const handleImageClick = () => {
        window.location.href = "https://www.gov.br/saude/pt-br";
    };
    return (
        <div className='footer'>
            <div className="footer_icons">
                <div className="footer_icon">
                    <img src="/icons/icon-email.png" alt="Ícone de Email"/>
                    <p>andreza.guimaraes@outlook.com</p>
                </div>

                <div className="footer_icon">
                    <img src="/icons/icon-fone.png" alt="Ícone de Telefone"/>
                    <p>(87) 99995-1608</p>
                </div>
            </div>


            <img src="/images/SUS-logo.png" alt="Ministério da Saúde" onClick={handleImageClick} />


        </div>
    );
}

export default Footer;