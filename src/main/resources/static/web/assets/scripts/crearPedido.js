const { createApp } = Vue
createApp({
    data() {
      return {
        tipoRetir:undefined,
        direccion:undefined,
        listaComida:undefined,
        listaBebida:undefined,
      }
    },
    created(){

    },
    methods:{
       /*  sweetHacerReserva(){
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Quiero mi reserva!'
              }).then((result) => {
                if (result.isConfirmed) {
                 this.hacerReserva()
                }
              })
        }, */
     
        hacerPedido() {
            console.log(this.tipoRetiro, this.direccion,this.listaComida, this.listaBebida)
            axios.patch(`/api/crear/pedido/usuario`, `holi`)
                .then(response => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Su reserva fue exitosa',
                        showConfirmButton: false,
                        timer: 1500
                      })
                })
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

    }
  }).mount('#app')