<template>

  <h3>Main</h3>
  <p>搜索内容为：{{ search }}</p>
  <search @searchEvent="getSearch"/>

</template>
<script >
import search from "./search.vue"
export default{
  data(){
    return{
      search:""
    }
  },
  components:{
    search
  },
  methods:{
    getSearch(data){
      this.search=data;
    }
  }

}

</script>



