<script setup>
import { ref, watch } from "vue";

const telefono = defineModel("telefono", { type: String }); // v-model desde el padre

// Validación en tiempo real: solo números
watch(telefono, (newVal) => {
  telefono.value = newVal.replace(/\D/g, ""); // elimina todo lo que no sea dígito
});
</script>

<template>
  <div class="mb-3">
    <label class="form-label">Número telefónico</label>
    <input
      v-model="telefono"
      type="text"
      class="form-control"
      maxlength="10"
      placeholder="10 dígitos"
      required
    >
    <small class="text-muted">Debe contener exactamente 10 números.</small>
  </div>
</template>
