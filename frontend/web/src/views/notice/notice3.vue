
<template>

  <div class="notice"> 



      <v-header :activeIndex='activeIndex+""'></v-header>

      <el-container class="mid" justify="center">

        <el-aside width="300px" style="background-color: white" >

          <div id="asidetitle" >
              通知公告
          </div>

          <el-menu
            default-active="3"
            class="asidemenu"
            :router="true">
            <el-menu-item index="1" route="/notice1">
              <span slot="title">规章制度</span>
            </el-menu-item>
            <el-menu-item index="2" route="/notice2">
              <span slot="title">教育培养</span>
            </el-menu-item>            
            <el-menu-item index="3" route="/notice3">
              <span slot="title">招聘招生</span>
            </el-menu-item>
          </el-menu>

        </el-aside>

        <el-main width="900px">

          <!-- 路径导航 -->
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/notice1' }">通知公告</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/notice3' }">招聘招生</el-breadcrumb-item>
          </el-breadcrumb>

          <div id="nltitle" >
            <h1>  招聘招生  </h1>
          </div>

          <template>
            <el-table
              ref="multipleTable"
              :data="newData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
              stripe
              style="width: 100%" 
              :show-header="false"
              @row-click="clickTr">
              <el-table-column
                prop="nl_title"
                width="650" 
               >
              </el-table-column>
              <el-table-column
                prop="realTime"
                width="150">
              </el-table-column>
            </el-table>
          </template>

          <!-- 分页 -->
          <div class="block" v-if="true"  >
            <el-pagination
              background 
              :small="true"
              :pager-count="5"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="pagesize"
              layout="total, prev, pager, next, sizes"
              :total="totalcount">
            </el-pagination>
          </div>



        </el-main>

      </el-container>

      <v-footer></v-footer>




  </div>  

</template>

<style>
  .notice {
    background-image:url('../../assets/images/banner2.png');
    background-repeat:no-repeat;
    background-size:100%;
    background-attachment:fixed
  }

   

  .el-menu-item{
    position:relative;
    left:312px;
  } 

  .mid{
    background-color:white;
    width: 1200px;
    position: relative;
    margin: auto;
  }

  .el-aside {
    position: relative;
    height: 550px;
  }

  #asidetitle {
    width:200px;
    position: relative;
    height: 40px;
    font-size: 30px;
    font-weight: 900;
    color: #666;
    left:50px;
    top:20px;
  }

  .asidemenu{
    width:270px;
    position: relative;
    height: 400px;
    top: 50px;
    color: #666;
    margin: auto;
    text-align: center;
  }

  .asidemenu .el-menu-item{
    width:200px;
    position: relative;
    height: 60px;
    font-size: 20px;
    font-weight: 900;
    color: #666;
    left:30px;
    margin: auto;
    text-align: center;
  }
  
  .el-main {
    position: relative;
    margin-right: 50px;
  }

  #nltitle{
    text-align: center;
    width: 750px;
    position: relative;
    font-size: 30px;
    color:rgb(57, 142, 243);
    font-weight: 600;
    margin: 20px;
  }

  .tablerow {
    box-shadow: 0 0px 3px 0 rgba(0, 0, 0, 0.1) ;
    height:40px;
    position: relative;
    margin: auto;
    width:100%;
    padding: 12px 0;
  }

  .block{
    display: flex;
    justify-content: center;
  }

  
  
</style>

<script >
  import Axios from 'axios'
  import NoticeInfo from './noticeInfo.vue'
  import webheader from "../webheader.vue";
  import webfooter from "../webfooter.vue";
  export default {
    name : 'notice',
    components:{    
      'v-header':webheader,
      'v-footer':webfooter,
      NoticeInfo
    },
    data() {
      return {
        activeIndex:4,
        tableData: [],
        newData:[],
        totalcount:0,
        currentPage:1,
        pagesize:10,
      };
 
    },
    mounted() {
      this.getData();
    },    
    methods: {
      clickTr(row, event, column){
        console.log(row.nl_id);
        if(row.nl_url==""){
          this.$router.push({name:'noticeInfo',query:{nl_id:row.nl_id}})
        }
        else{
          window.location.href = row.nl_url
        }
      },
      handle(row,column,event,cell){
        console.log(row)
        console.log(column)
        console.log(event)
        console.log(cell)
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSizeChange: function (size) {
              this.pagesize = size;
              console.log(this.pagesize)  //每页下拉显示数据
      },
      handleCurrentChange: function(currentPage){
              this.currentPage = currentPage;
              console.log(this.currentPage)  //点击第几页
      },
      getData() {
        Axios.get('/api/notice/findAll').then(response => {
            this.tableData=response.data.result;
              for(var i = 0;i<this.tableData.length;i++){
                if (this.tableData[i].nl_state==1&&this.tableData[i].nl_nl_subType_name=="招聘招生"){
                  console.log(this.tableData[i].nl_title);
                  this.newData.push(this.tableData[i]);
                  this.totalcount=this.newData.length;
                  console.log(this.totalcount);
                  console.log(this.newData);
                }
              }
              console.log(this.tableData);
        }, response => {
            console.log("error");
          });
      },
    },
  }

</script>