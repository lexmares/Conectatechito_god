<script setup>
import { ref, watch } from "vue";

const correoInstitucional = defineModel("correoInstitucional", { type: String });
const errorCorreo = ref("");

// Regex: debe terminar en @orizaba.tecnm.mx
const regexCorreo = /^[a-zA-Z0-9._%+-]+@orizaba\.tecnm\.mx$/;

// Validación en tiempo real
watch(correoInstitucional, (newVal) => {
  if (!regexCorreo.test(newVal)) {
    errorCorreo.value = "El correo debe pertenecer al dominio @orizaba.tecnm.mx";
  } else {
    errorCorreo.value = "";
  }
});
</script>

<template>
  <div class="mb-3">
    <label class="form-label">Correo institucional</label>
    <input
      v-model="correoInstitucional"
      type="email"
      class="form-control"
      :class="{ 'is-invalid': errorCorreo }"
      required
      placeholder="usuario@orizaba.tecnm.mx"
    >
    <div v-if="errorCorreo" class="invalid-feedback">
      {{ errorCorreo }}
    </div>
    <small v-else class="text-muted">
      Debe ser un correo válido del dominio @orizaba.tecnm.mx
    </small>
  </div>
</template>
