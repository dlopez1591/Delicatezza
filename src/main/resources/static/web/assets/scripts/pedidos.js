const app = Vue.createApp({
    data() {
      return {
        usuarioAutenticado: {}
      }
    },
    created() {
      this.loadUsuarios();
    },
    methods: {
      loadUsuarios() {
        axios.get("/api/usuario/autenticado")
          .then((response) => {
            this.usuarioAutenticado = response.data;
          })
          .catch((error) => console.log("Something went wrong"));
      },
    }
  });
  
  app.mount('#app');