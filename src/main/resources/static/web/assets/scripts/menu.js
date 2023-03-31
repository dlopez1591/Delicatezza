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
       comidaEnMesa: undefined,
       listitaPedido: [],
       listaComidasId: [],
       listaBebidasId: [],
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
        console.log("comida en mesa: ",this.comidaEnMesa.nombre,
                    " | id de la comida",this.comidaEnMesa.id)
      },
      añadirAlCarrito(idComida, cantidadSolicitada){
        console.log("idComida: ",idComida," | cantidad Solicitada: ", cantidadSolicitada)
        let miniPedido = [idComida, cantidadSolicitada]
        console.log(miniPedido)
        this.listitaPedido.push(miniPedido)
        console.log(this.listitaPedido)
        return this.listitaPedido
    },
    agregarComidas(idComida, cantidadSolicitada){

    },
    agregarBebidas(idComida, cantidadSolicitada){

    },
    pedido(listaBebidasId,listaComidasId,tipoRetiro, direccion){
      // axios.post
    }

    }
  }).mount('#app')


