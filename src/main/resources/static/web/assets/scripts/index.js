const { createApp } = Vue

createApp({
    data() {
        return {
            email: '',
            password: '',
            error: '',
            nombre: '',
            apellido: '',
            telefono: '',
            cambio: 1,
        }
    },
    methods: {
        login() {
            axios.post('/api/login', `email=${this.email}&password=${this.password}`, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
                .then(response => {
                    Swal.fire({
                        position: 'midle',
                        icon: 'success',
                        color: 'var(--bs-highlight-bg)',
                        background: '#000000c1',
                        text: 'Your login have been successful',
                        showConfirmButton: false,
                        timer: 2000
                    })
                        .then(() => {
                            if (this.email == "admin@gmail.com") {
                                window.location.href = 'web/admin.html';
                            } else {
                                window.location.href = '/web/pedidos.html';
                            }
                        })
                })
                .catch(error => {
                    this.error = error.response.data.message;
                    Swal.fire({
                        icon: 'error',
                        color: 'var(--bs-highlight-bg)',
                        background: '#000000c1',
                        text: 'Revisa tus datos!',
                    })
                });
        },
        cambiar() {
            if (this.cambio == 0)
                this.cambio = 1;
            else
                this.cambio = 0
        },
        register() {
                axios.post('/api/crear/usuario', `nombre=${this.nombre}&apellido=${this.apellido}&email=${this.email}&password=${this.password}&telefono=${this.telefono}`, {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                    .then(response => {
                        Swal.fire({
                            position: 'midle',
                            icon: 'success',
                            color: 'var(--bs-highlight-bg)',
                            background: '#000000c1',
                            text: 'User created successfully',
                            showConfirmButton: false,
                            timer: 2000
                        })
                        this.login();
                    })
                    .catch(error => {
                        this.error = error.response.data.message;
                    });
        },
    }
}).mount('#app')