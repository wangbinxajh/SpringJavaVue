<template>
   <div style="font-size: 12px; line-height: 60px; display: flex">
       <div style="flex: 1; ">
           <span :class="collapseBtnClass" style="cursor: pointer;font-size: 20px" @click="collapse"></span>
           <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
               <el-breadcrumb-item :to="'/'" >首页</el-breadcrumb-item>
               <el-breadcrumb-item>{{pathname}}</el-breadcrumb-item>
           </el-breadcrumb>
       </div>

       <el-dropdown style="width: 150px; cursor: pointer; text-align:right" >
           <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
           <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
               <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                    <router-link to="/person">个人信息</router-link>
               </el-dropdown-item>
               <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                   <span  style="text-decoration: none"  @click="logout">退出</span>
               </el-dropdown-item>

           </el-dropdown-menu>
       </el-dropdown>
   </div>
</template>

<script>
    export default {
        name: "Header",
        props:{
            collapseBtnClass: String,
            collapse: Boolean
        },
        watch: {
            '$route': function (){
                this.pathname = localStorage.getItem("pathname")

            }
        },
        data(){
            return{
                user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")):{"ww":1}

            }
        },
        methods: {
            logout(){
                this.$router.push("/login")
                localStorage.removeItem("user")
                this.$message.success("推出成功")
            }

        },
        created() {
            console.log()
        }

    }
</script>

<style scoped>

</style>