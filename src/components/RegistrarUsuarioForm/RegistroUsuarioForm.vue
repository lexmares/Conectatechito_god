<script setup>
import { ref } from "vue";
import { crearUsuario } from "../../services/api.js";

import NombreInput from "./NombreInput.vue";
import MatriculaInput from "./MatriculaInput.vue";
import CorreoInput from "./CorreoInput.vue";
import PasswordInput from "./PasswordInput.vue";
import TelefonoInput from "./TelefonoInput.vue";

const nombres = ref("");
const apellidoPaterno = ref("");
const apellidoMaterno = ref("");
const matricula = ref("");
const correoInstitucional = ref("");
const contrasena = ref("");
const confirmarContrasena = ref("");
const telefono = ref("");

const registrar = () => {
  function capitalizarPalabras(texto) {
  return texto
    .toLowerCase()
    .split(" ")
    .filter(p => p.trim() !== "") // elimina espacios extra
    .map(p => p.charAt(0).toUpperCase() + p.slice(1))
    .join(" ");
}

  let nombreCompleto = `${nombres.value} ${apellidoPaterno.value} ${apellidoMaterno.value}`.trim();

  nombreCompleto = capitalizarPalabras(nombreCompleto);

  const nuevoUsuario = {
    nombreCompleto,
    matricula: matricula.value,
    correoInstitucional: correoInstitucional.value,
    contrasena: contrasena.value,
    telefono: telefono.value,
    rol: "comprador"
  };

  crearUsuario(nuevoUsuario)
    .then(() => {
      alert("Usuario registrado con éxito");
      nombres.value = "";
      apellidoPaterno.value = "";
      apellidoMaterno.value = "";
      matricula.value = "";
      correoInstitucional.value = "";
      contrasena.value = "";
      telefono.value = "";
    })
    .catch(err => console.error("Error al registrar:", err));
};
</script>

<template>
      <h3 class="text-center mb-4">Crear cuenta</h3>
  <form @submit.prevent="registrar">


    <NombreInput 
      v-model:nombres="nombres" 
      v-model:apellidoPaterno="apellidoPaterno" 
      v-model:apellidoMaterno="apellidoMaterno" 
    />
    <MatriculaInput v-model:matricula="matricula" />
    <CorreoInput v-model:correoInstitucional="correoInstitucional" />
    <PasswordInput v-model:contrasena="contrasena" />
    <TelefonoInput v-model:telefono="telefono" />
    <button type="submit" class="btn btn-success w-100">Crear cuenta</button>
    
    <p class="text-center mt-3">
        ¿Ya tienes cuenta?
         <router-link to="/login">Inicia sesión</router-link>
      </p>
  </form>
</template>
