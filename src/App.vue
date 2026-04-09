<script setup>
</script>

<template>
  <div class="bg-light min-vh-100 d-flex flex-column">
    <!-- Header con logo -->
    <div class="text-center py-4 bg-white shadow-sm">
      <img src="/img/conectatec1.png" alt="ConectaTEC Logo" style="max-width: 250px;">
    </div>

    <!-- Contenedor principal -->
    <div class="container py-5 flex-grow-1">
      <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">
          <div class="card p-4 shadow" style="width: 100%; max-width: 500px;">
            <!-- Aquí Vue Router renderiza el componente según la ruta -->
            <router-view />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
