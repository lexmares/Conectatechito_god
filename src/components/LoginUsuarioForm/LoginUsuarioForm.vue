<script setup>
import { ref } from "vue";
import axios from "axios";

const correoInstitucional = ref("");
const contrasena = ref("");
const errorLogin = ref("");
const isLoading = ref(false);

async function login() {
  // Validación previa
  if (!correoInstitucional.value || !contrasena.value) {
    errorLogin.value = "Por favor, completa todos los campos.";
    return;
  }

  isLoading.value = true;
  errorLogin.value = "";

  try {
    const { data } = await axios.post("http://localhost:8080/login", {
      correoInstitucional: correoInstitucional.value,
      contrasena: contrasena.value
    });

    console.log("Login exitoso:", data);

    // Guardar token y redirigir
    localStorage.setItem("token", data.token);
    window.location.href = "/dashboard"; 
  } catch (err) {
    errorLogin.value = "Correo o contraseña incorrectos";
    console.error("Error en login:", err.response?.data || err.message);
  } finally {
    isLoading.value = false;
  }
}
</script>

<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="card p-4  border-0" style="width: 100%; max-width: 400px;">
      <h3 class="text-center mb-4">Iniciar sesión</h3>

      <form @submit.prevent="login">
        <div class="mb-3">
          <label class="form-label">Correo institucional</label>
          <input 
            v-model="correoInstitucional" 
            type="email" 
            class="form-control" 
            required 
            placeholder="correo@tec.mx"
            autocomplete="email"
            aria-label="Correo institucional"
          >
        </div>

        <div class="mb-3">
          <label class="form-label">Contraseña</label>
          <input 
            v-model="contrasena" 
            type="password" 
            class="form-control" 
            required
            autocomplete="current-password"
            aria-label="Contraseña"
          >
        </div>

        <button type="submit" class="btn btn-primary w-100" :disabled="isLoading">
          {{ isLoading ? "Cargando..." : "Iniciar sesión" }}
        </button>
      </form>

      <p v-if="errorLogin" class="text-danger text-center mt-2">{{ errorLogin }}</p>

      <p class="text-center mt-3">
        ¿No tienes cuenta?
        <router-link to="/registro">Regístrate</router-link>
      </p>
    </div>
  </div>
</template>
