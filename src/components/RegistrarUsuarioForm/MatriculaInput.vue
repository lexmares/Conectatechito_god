<script setup>
import { ref, watch } from "vue";

const matricula = defineModel("matricula", { type: String });
const errorMatricula = ref("");

// Validación en tiempo real: solo números
watch(matricula, (newVal) => {
  // Elimina cualquier carácter que no sea número
  matricula.value = newVal.replace(/\D/g, "");

  // Validación de longitud
  if (matricula.value.length !== 8) {
    errorMatricula.value = "La matrícula debe contener exactamente 8 dígitos.";
  } else {
    errorMatricula.value = "";
  }
});
</script>

<template>
  <div class="mb-3">
    <label class="form-label">Número de Control</label>
    <input
      v-model="matricula"
      type="text"
      class="form-control"
      :class="{ 'is-invalid': errorMatricula }"
      maxlength="8"
      required
      placeholder="Ejemplo: 12345678"
    >
    <div v-if="errorMatricula" class="invalid-feedback">
      {{ errorMatricula }}
    </div>
    <small v-else class="text-muted">
      Debe contener exactamente 8 dígitos numéricos.
    </small>
  </div>
</template>
