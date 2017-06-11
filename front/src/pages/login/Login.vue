<template>
    <div class="login-bg">
        <div class="form-div">

        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                form : {
                    username: '',
                    password: ''
                }
            }
        },
        methods:{
            onLogin(){

            }
        }
    }
</script>
<style>
    .title {
        text-align: center;
    }
    .login-bg {
        background-color: #E5E9F2;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
    }
    .form-div{
        padding: 5% 10% 5% 10%;

    }
</style>