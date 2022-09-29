<template>
    <div class="wrapper">
        <div style="margin: 200px auto ; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登陆</b></div>
            <el-form :model="user" :rules="rules">
                <el-form-item prop="name">
                    <el-input  size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.name"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
                </el-form-item>
                <el-form-item style="margin: 10px 0 ; text-align: right">
                  <el-button type="primary" size="small" aria-autocomplete="off" @click="login">登陆</el-button>
                    <el-button type="warning" size="small" aria-autocomplete="off" @click="$router.push('/register')">注册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>

</template>

<script>
    export default {
        name: "Login",
        data(){
            return{
                user:{},
                rules: {
                    name: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 6, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                    ]
                }
            }
        },
        methods:{
            login(){
                this.request.post("http://localhost:9090/user/login",this.user).then(res =>{
                    if (res.code === '200'){

                        localStorage.setItem("user",JSON.stringify(res.data))
                        this.$router.push("/")
                        this.$message.success("登陆成功")
                    }else {
                        this.$message.error(res.msg)
                    }
                })
            }
        }
    }
</script>

<style>
.wrapper{
    height: 100vh;
    background-image: linear-gradient(to bottom right,#FC4668,#3F5EFB);
    overflow: hidden;
}
</style>