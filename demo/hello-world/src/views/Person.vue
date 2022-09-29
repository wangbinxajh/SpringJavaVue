<template>
    <el-card style="width: 500px; padding: 20px; border: 1px solid #ccc">
        <el-form label-width="80px" size="small">
            <el-form-item label="用户名">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="年龄">
                <el-input v-model="form.age" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item>

                <el-button type="primary" @click="save">确 定</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
    export default {
        name: "Person",
        data(){
            return{
                form: {},
                user: localStorage.getItem("user")? JSON.parse(localStorage.getItem("user")) :{}
            }

        },
        created() {
            this.request.get("http://localhost:9090/user/name/" + this.user.name).then(res =>{
                if(res.code == 200){
                    this.form = res.data
                }

            })
        },
        methods:{
            save(){
                this.request.post("http://localhost:9090/user/",this.form).then(res=>{
                    if (res.data){
                        this.$message.success("保存成功")
                        this.load()
                    }else {
                        this.$message.error("保存失败")
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>