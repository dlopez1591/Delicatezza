const app = Vue.createApp({
    data() {
      return {
        usuarioAutenticado: {},
        cantidadPersonas: undefined,
        fechaString: undefined,
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
      hacerReserva() {
        console.log(this.cantidadPersonas, this.fechaString)
        axios.post(`/api/crear/reserva`, `cantidadPersonas=${this.cantidadPersonas}&fechaString=${this.fechaString}`)
            .then(response => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    color: 'var(--bs-highlight-bg)',
                    background: '#000000c1',
                    text: 'Su reserva fue exitosa',
                    confirmButtonText: 'ir a mis pedidos',
                    showConfirmButton: true,
                  }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = '/web/pedidos.html';
                    }
            })})
            .catch(error => {
                console.log(error)
                this.error = error.response.data.message; 
                console.log(error.response.data)
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: error.response.data
                    
                  })
            });
      },
      logOut(){
        axios.post("/api/logout")
        .then(response =>{
          Swal.fire({
            position: 'center',
            icon: 'success',
            color: 'var(--bs-highlight-bg)',
            background: '#000000c1',
            text: 'cierre de sesion exitosa',
            confirmButtonText: 'ok',
            showConfirmButton: true,
          }).then((result) =>{
            if (result.isConfirmed){
              window.location.href = '/web/index.html';
            }
          })
        })
      }
    }
  });
  
  app.mount('#app');