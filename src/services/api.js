import axios from "axios";

const API_URL = "http://localhost:8080/usuarios";

export async function crearUsuario(usuario) {
  try {
    const { data } = await axios.post(API_URL, usuario);
    return data;
  } catch (error) {
    console.error("Error al registrar:", error.response?.data || error.message);
    throw error;
  }
}
