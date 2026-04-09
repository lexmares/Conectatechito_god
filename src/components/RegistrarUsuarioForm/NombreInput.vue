<script setup>
import { ref, watch } from "vue";

// Modelos para conectar con el padre
const nombres = defineModel("nombres", { type: String });
const apellidoPaterno = defineModel("apellidoPaterno", { type: String });
const apellidoMaterno = defineModel("apellidoMaterno", { type: String });

// Regex: solo letras y espacios
//const regexSoloLetras = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]*$/;


const regexNoPermitidos = /[^A-Za-zÁÉÍÓÚáéíóúÑñ\s]/g;



// Validación en tiempo real
watch(nombres, (newVal) => {
  nombres.value = (newVal.replace(regexNoPermitidos, ""));});
watch(apellidoPaterno, (newVal) => {
  apellidoPaterno.value = (newVal.replace(regexNoPermitidos, ""));});
watch(apellidoMaterno, (newVal) => {
  apellidoMaterno.value = (newVal.replace(regexNoPermitidos, ""));});
</script>

<template>
  <div class="mb-3">
    <label class="form-label">Nombre(s)</label>
    <input 
      v-model="nombres" 
      type="text" 
      class="form-control" 
      required 
      placeholder="Ejemplo: Juan Carlos"
    >
    <small class="text-muted">Solo letras y espacios.</small>
  </div>

  <div class="mb-3">
    <label class="form-label">Apellido paterno</label>
    <input 
      v-model="apellidoPaterno" 
      type="text" 
      class="form-control" 
      required 
      placeholder="Ejemplo: García"
    >
    <small class="text-muted">Solo letras y espacios.</small>
  </div>

  <div class="mb-3">
    <label class="form-label">Apellido materno</label>
    <input 
      v-model="apellidoMaterno" 
      type="text" 
      class="form-control" 
      required 
      placeholder="Ejemplo: López"
    >
    <small class="text-muted">Solo letras y espacios.</small>
  </div>
</template>
