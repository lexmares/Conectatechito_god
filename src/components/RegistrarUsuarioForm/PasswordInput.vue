<script setup>
import { ref, watch } from "vue";

const contrasena = defineModel("contrasena", { type: String });
const confirmarContrasena = defineModel("confirmarContrasena", { type: String });

const errorPassword = ref("");
const errorConfirmacion = ref("");
const mostrarPassword = ref(false);
const mostrarConfirmacion = ref(false);

// Regex estricta: mínimo 8 caracteres, al menos una minúscula, una mayúscula, un número y un signo especial
const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@_.*!%?&])[A-Za-z\d@_.*!%?&]{8,}$/;

// Validación en tiempo real de la contraseña
watch(contrasena, (newVal) => {
  if (!regexPassword.test(newVal)) {
    errorPassword.value =
      "Debe tener mínimo 8 caracteres, incluir mayúscula, minúscula, número y signo especial (@ _ . * ! % ? &).";
  } else {
    errorPassword.value = "";
  }

  // Validar confirmación también
  if (confirmarContrasena.value && newVal !== confirmarContrasena.value) {
    errorConfirmacion.value = "Las contraseñas no coinciden.";
  } else {
    errorConfirmacion.value = "";
  }
});

// Validación en tiempo real de la confirmación
watch(confirmarContrasena, (newVal) => {
  if (newVal !== contrasena.value) {
    errorConfirmacion.value = "Las contraseñas no coinciden.";
  } else {
    errorConfirmacion.value = "";
  }
});
</script>

<template>
  <!-- Campo contraseña -->
  <div class="mb-3">
    <label class="form-label">Contraseña</label>
    <div class="input-group">
      <input
        v-model="contrasena"
        :type="mostrarPassword ? 'text' : 'password'"
        class="form-control"
        :class="{ 'is-invalid': errorPassword }"
        required
      >
      <button
        type="button"
        class="btn btn-outline-secondary"
        @click="mostrarPassword = !mostrarPassword"
      >
        {{ mostrarPassword ? '🙈 Ocultar' : '👁️ Mostrar' }}
      </button>
      <div v-if="errorPassword" class="invalid-feedback">
        {{ errorPassword }}
      </div>
    </div>
    <small v-if="!errorPassword" class="text-muted">
      La contraseña debe tener mínimo 8 caracteres, incluir mayúscula, minúscula, número y signo especial.
    </small>
  </div>

  <!-- Campo confirmación -->
  <div class="mb-3">
    <label class="form-label">Confirmar contraseña</label>
    <div class="input-group">
      <input
        v-model="confirmarContrasena"
        :type="mostrarConfirmacion ? 'text' : 'password'"
        class="form-control"
        :class="{ 'is-invalid': errorConfirmacion }"
        required
      >
      <button
        type="button"
        class="btn btn-outline-secondary"
        @click="mostrarConfirmacion = !mostrarConfirmacion"
      >
        {{ mostrarConfirmacion ? '🙈 Ocultar' : '👁️ Mostrar' }}
      </button>
      <div v-if="errorConfirmacion" class="invalid-feedback">
        {{ errorConfirmacion }}
      </div>
    </div>
  </div>
</template>
