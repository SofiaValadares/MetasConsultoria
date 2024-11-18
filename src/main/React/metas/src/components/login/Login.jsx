import { FaUser, FaLock} from "react-icons"

const login = () => {
    return (
        <div className="container">
            <form>
                <h1>login</h1>
                <div>
                    <input type="email" placeholder="E-mail"></input>
                    <FaUser className = "icon"/>
                </div>
                <div>
                    <input type="password"placeholder="Senha"/>
                    <FaLock className = "icon"/>
                </div>
                <button>Entrar</button>
            </form>
        </div>
    );
};

export default  login;