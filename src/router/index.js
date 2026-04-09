import { createRouter, createWebHistory } from "vue-router";
import RegistroUsuarioForm from "../components/RegistrarUsuarioForm/RegistroUsuarioForm.vue";
import LoginForm from "../components/LoginUsuarioForm/LoginUsuarioForm.vue";

const routes = [
  { path: "/registro", component: RegistroUsuarioForm },
  { path: "/login", component: LoginForm },
  { path: "/", redirect: "/registro" } // opcional: redirigir raíz a login
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
