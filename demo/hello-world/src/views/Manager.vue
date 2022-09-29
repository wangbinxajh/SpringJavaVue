<template>
  <div sytle="height: 100%">
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
        <Aside :width="sideWidth+'px'" style="box-shadow: 2px 0 6px rgb(0 21 41/35%);">
            <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
        </Aside>
    </el-aside>

    <el-container>
      <el-header style="border-bottom: 1px solid #ccc; ">
            <Header :collapseBtnClass="collapseBtnClass" :collapse="Collapse"  />
      </el-header>

      <el-main>
          <router-view/>
      </el-main>
    </el-container>
  </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import Aside from '@/components/Aside';
import Header from "@/components/Header";
// import request from "@/utils/request";


export default {
  name: 'HomeView',
  data(){
     return {
        collapseBtnClass: 'el-icon-s-fold',
        isCollapse: false,
        sideWidth: 200,
        logoTextShow: true
     }
  },

    components: {
      Aside,
      Header
    },

    methods:{
     Collapse(){   //点击收缩按钮
        this.isCollapse=!this.isCollapse
        if (this.isCollapse){
            this.sideWidth = 64
            this.collapseBtnClass = 'el-icon-s-unfold'
            this.logoTextShow = false
        }else{
            this.sideWidth = 200
            this.collapseBtnClass = 'el-icon-s-fold'
            this.logoTextShow = true
        }
    },

  }
}
</script>






