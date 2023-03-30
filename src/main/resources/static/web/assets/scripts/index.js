const { createApp } = Vue

createApp({
    data() {
        return {
            email: '',
            password: '',
            error: '',
            firstName: '',
            lastName: '',
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
                                window.location.href = '/manager.html';
                            } else {
                                window.location.href = '/web/accounts.html';
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
            if (/^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$/.test(this.email)) {
                axios.post('/api/clients', `first=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`, {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                    .then(response => {
                        this.login();
                    })
                    .catch(error => {
                        this.error = error.response.data.message;
                    });
            }

        },
    }
}).mount('#app')