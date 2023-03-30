const { createApp } = Vue
createApp({
  data() {
    return {
      usuarios: [],
      usuarioSeleccionado: {}
    }
  },
  created(){
    this.loadUsuarios();
  },
  methods:{
    loadUsuarios(){
      axios.get("/api/usuario")
      .then((response)=> {
        this.usuarios = response.data;
      })
      .catch((error) => console.log("Something went wrong"));
    },
    pasarUsuarioModal(usuario){
      this.usuarioSeleccionado = usuario;
      console.log(this.usuarioSeleccionado)
    },
    actualizarUsuario() {
      axios.put("/api/actualizar/usuario", {
        id: this.usuarioSeleccionado.id,
        nombre: this.usuarioSeleccionado.nombre,
        apellido: this.usuarioSeleccionado.apellido,
        email: this.usuarioSeleccionado.email,
        telefono: this.usuarioSeleccionado.telefono
      })
      .then((response) => {
        Swal.fire({
          title: 'Cambio exitoso',
          text: 'El usuario ha sido actualizado correctamente',
          icon: 'success',
          confirmButtonText: 'Aceptar'
        }).then(()=> {
          window.location.href = '/web/admUsuario.html'
        });
      })
      .catch((error) => {
        Swal.fire({
          title: 'Error',
          text: 'No se ha podido actualizar el usuario',
          icon: 'error',
          confirmButtonText: 'Aceptar'
        });
      });
    },
    borrarUsuario() {
      const email = this.usuarioSeleccionado.email;
      const url = `/api/borrar/usuario?email=${email}`;
      console.log(url); // imprime la URL formada en la consola
      axios.delete(url)
        .then(response => {
          Swal.fire({
            title: 'Eliminación exitosa',
            text: 'El usuario ha sido eliminado correctamente',
            icon: 'success',
            confirmButtonText: 'Aceptar'
          }).then(() => {
            this.loadUsuarios();
          });
        })
        .catch(error => {
          Swal.fire({
            title: 'Error',
            text: 'No se ha podido eliminar el usuario',
            icon: 'error',
            confirmButtonText: 'Aceptar'
          });
        });
    }
    
    
  }
}).mount('#app');
