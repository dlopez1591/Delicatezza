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
       comidaEnMesa: {
        "id": 1,
       "nombre": "Panchito",
       "description": "ia tu sabe",
       "tipoComida": "PRINCIPAL",
       "precio": 250.0,
       "imagen": "urlImagen",
       "disponibilidad": true},
       listitaPedido: [],
       listaComidasId: [],
       listaBebidasId: [],
       tipoRetiro: undefined,
        direccion: undefined,
        total: 0,
        number: undefined,
        cvv: undefined
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

      agregarComidas(idComida, cantidadSolicitada){
        //console.log("idComida: ",idComida," | cantidad Solicitada: ", cantidadSolicitada)
        let miniPedido = [idComida, cantidadSolicitada]
        //console.log(miniPedido)
        this.listaComidasId.push(miniPedido)
        //console.log(this.listitaPedido)
        this.total += this.comidaEnMesa.precio * cantidadSolicitada
        this.listitaPedido.push(this.comidaEnMesa)
        return this.listaComidasId
      },

      agregarBebidas(idBebida, cantidadSolicitada){
        //console.log("idComida: ",idBebida," | cantidad Solicitada: ", cantidadSolicitada)
        let miniPedido = [idBebida, cantidadSolicitada]
        //console.log(miniPedido)
        this.listaBebidasId.push(miniPedido)
        //console.log(this.listaBebidasId)
        this.total += this.comidaEnMesa.precio * cantidadSolicitada
        this.listitaPedido.push(this.comidaEnMesa)
        return this.listaBebidasId
      },

      crearPedido(){
        console.log(this.total)
        console.log(this.listaComidasId,this.listaBebidasId)
       axios.post("/api/crear/pedido/usuario",{"tipoRetiro":this.tipoRetiro,"direccion":this.direccion,"listaComidasId":this.listaComidasId,"listaBebidasId":this.listaBebidasId})
        .then(
          axios.post("https://mindhub-brother-homebanking-production.up.railway.app/api/card/payment",{
            "cardNumber":this.number,
            "cardCvv":this.cvv,
            "operationAmount":this.total,
            "operationDescription":"Delicatezza payment"
        }))
        .then(
          Swal.fire({
            position: 'midle',
            icon: 'success',
            color: 'var(--bs-highlight-bg)',
            background: '#000000c1',
            text: 'Pedido realizado con exito',
            showConfirmButton: true,
            confirmButtonText: 'ir a mis pedidos',
            })
            .then((result) => {
              if (result.isConfirmed) {
                window.location.href = '/web/pedidos.html';
              }
            })
          )
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

