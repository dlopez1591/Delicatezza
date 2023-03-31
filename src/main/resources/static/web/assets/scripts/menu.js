const { createApp } = Vue
createApp({
    data() {
      return {
       comidas:undefined,
       principal: undefined,
       guarnicion: undefined,
       postre: undefined,
       bebidas: undefined,
       cantidadSolicitada: 1,
       comidaEnMesa: undefined
      }
    },
    created(){
      this.cargarComidas()
      this.cargarBebidas()
    },
    methods:{
      cargarComidas(){
        axios.get("http://localhost:8080/api/comidas")
        .then(response => {
          this.comidas = response.data
          this.principal = this.comidas.filter( comida=> comida.tipoComida == "PRINCIPAL")
          this.guarnicion = this.comidas.filter( comida=> comida.tipoComida == "GUARNICION")
          this.postre = this.comidas.filter( comida=> comida.tipoComida == "POSTRE")
        })
        .catch(err => err.mesagge)
      },

      cargarBebidas(){
        axios.get("http://localhost:8080/api/bebidas")
        .then(response => {
          this.bebidas = response.data
        })
        .catch(err => err.mesagge)
      },

      alertAgregar(comida){
        this.comidaEnMesa = "",
        this.comidaEnMesa = comida
        console.log("comida en mesa: ",this.comidaEnMesa,
                    " | id de la comida",this.comidaEnMesa.id)
      },
      añadirAlCarrito(idComida,cantidadSolicitada){
        console.log("idComida: ",idComida,"cantidad Solicitada", cantidadSolicitada)
      }

    }
  }).mount('#app')


