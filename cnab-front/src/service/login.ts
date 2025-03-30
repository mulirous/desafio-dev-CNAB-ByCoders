import axios, { HttpStatusCode } from "axios";
import Cookies from "js-cookie";

interface LoginFormData {
	email: string;
	password: string;
}

// A função retorna uma Promise, não uma string
const login = async (data: LoginFormData) => {
	try {
		const response = await axios({
			method: "post",
			url: "http://localhost:8080/auth/sign-in", // trocar pela url real na api após o deploy
			headers: {
				"Content-Type": "application/json",
			},
			data: {
				email: data.email,
				password: data.password,
			},
		});

		// Armazena o token JWT no cookie
		if (response.data.token) {
			Cookies.set("token", response.data.token, {
				expires: 7,
				path: "/",
			});
			// localStorage.setItem("token", response.data.token);
		}

		return response.data;
	} catch (error) {
		console.error("Erro ao fazer login:", error);
		throw error; // Propaga o erro para ser tratado pelo componente
	}
};

export default login;
