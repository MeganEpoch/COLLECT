<template>
  <div>
    <v-container class="pa-4">
      <template>
        <v-row class="ma-2">
          <v-chip
              class="ma-2"
              color="indigo darken-3"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            聚类报告列表
          </v-chip>
        </v-row>

        <el-row v-loading="loading" element-loading-text="数据加载中zzz">
          <div class="page-x">
            <!-- 【步骤1】 创建容器 -->
            <!--    <div class="g6-x" id="containerG6" ref="containerG6"></div>-->
            <div class="g6-x" id="container"></div>
          </div>
        </el-row>

        <div class="box">
          <v-expansion-panels>
            <v-expansion-panel
                v-for="Index in cluster_list"
                :key="Index"
            >
              <v-expansion-panel-header>
                <!--<<<<<<< HEAD-->
                <!--                <h3>聚合报告编号：{{cluster_list[Index]}}</h3>-->
                <!--                <p>聚类报告数量：{{number_list[Index]}}</p>-->
                <!--                <el-button @click="handleDetail(cluster_list[Index])">查看融合结果</el-button>-->
                <!--=======-->
                <v-col>
                  <h3>聚合报告编号：{{cluster_list[Index]}}</h3>
                  <br/><br/>
                  <p>聚类报告数量：{{number_list[Index]}}</p>
                  <el-button @click="handleDetail(cluster_list[Index])">查看融合结果</el-button>
                </v-col>
                <el-col v-loading="imgLoading" element-loading-text="正在疯狂计算词云">
                  <div class="img">
                    <img :src="wordCloud_list[Index]"  class="picture"/>
                  </div>
                </el-col>
              </v-expansion-panel-header>

              <v-expansion-panel-content>
                <el-divider></el-divider>
                <div
                    class="aggregateReport-area"
                    v-for="(aggregateReport, newIndex) in aggregateReportsList"
                    :key="(newIndex)"
                >
                  <div v-if="aggregateReport.reportCluster === cluster_list[Index]">
                    <v-card class="aggregateReport">
                      <p>报告详情：{{ aggregateReport.intro }}</p>
                      <span
                          class="aggregateReport-trigger"
                          @click="handlePeek(aggregateReport.reportId)"
                      >
                    报告详情
                  </span>
                    </v-card>
                  </div>
                </div>
                <!--              -->
                <!--              <row class="ma-3"></row>-->
                <!--              <el-divider class="divider"></el-divider>-->
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>


          <!--          <div>-->
          <!--            <h2>所有报告</h2>-->
          <!--              <div-->
          <!--                  class="aggregateReport-area"-->
          <!--                  v-for="(aggregateReport, n) in aggregateReportsList"-->
          <!--                  :key="(n)"-->
          <!--              >-->
          <!--                <v-card class="aggregateReport">-->
          <!--                  <p>报告聚类结果编号：{{aggregateReport.cluster}}</p>-->
          <!--                  <p>报告详情：{{ aggregateReport.text }}</p>-->
          <!--                  <span-->
          <!--                      class="aggregateReport-trigger"-->
          <!--                      @click="handlePeek"-->
          <!--                  >-->
          <!--                    报告详情-->
          <!--                  </span>-->
          <!--                </v-card>-->
          <!--              </div>-->
          <!--            </div>-->
          <v-row class="mt-16"></v-row>
        </div>
      </template>
      <v-row class="mt-8"></v-row>
    </v-container>
  </div>
</template>

<script>
// 引入G6  ⭐main.js全局引入似乎没有作用
import G6 from '@antv/g6'
import AggregateReportItem from "@/components/AggregateReportItem";
import { getReportCluster, getClustersTreeView,getWordCloud } from "@/api/report";

export default {
  name: "AggregateReport",
  components: {
    AggregateReportItem,
  },

  data() {
    return {
      msg: "",
      loading: true, //树形结构正在加载标志
      imgLoading: true, //词云加载标记
      aggregateReportsList:[],
      // list:[],  // 一个从0开始的整数序列
      number_list:[],  // 每个类的聚合报告数量
      cluster_list:[],  // 放簇号

      try_list : [], // 原始信息
      try_tree_date: {}, //树结构化之后信息
      tree_data: {},
      wordCloud_list:[],  //词云图
      getAnything: false, //是否获取聚类后结果的标志
    }
  },

  methods: {

    loadReports(){
      console.log("开始获取报告")
      // id: this.$route.params.taskId
      getReportCluster({ id: this.$route.params.taskId }).then(res => {
        console.log(res)
        this.aggregateReportsList = res;
        var tag = -1  //最开始的簇号
        var count = 1
        for (let num in this.aggregateReportsList) {
          // console.log(num, this.aggregateReportsList[num].reportCluster)
          if (Number(this.aggregateReportsList[num].reportCluster) !== tag) {
            tag = Number(this.aggregateReportsList[num].reportCluster)
            count = 1
            this.number_list.push(1)
            this.cluster_list.push(tag)
          } else {
            this.number_list.pop()
            count = count + 1
            this.number_list.push(count)
          }
        }
        console.log("获取报告完成")
        this.getAnything = true
        this.handleTree()
        // this.toAString()
        // this.toTree()
        // this.initTree()
        setTimeout(() => {
            console.log('等待2s');
        }, 8000);
        console.log("完成报告聚类计算")
        for(let index in this.cluster_list){
          getWordCloud({taskId: this.$route.params.taskId, cluster:this.cluster_list[index]}).then(res => {
                console.log("获取词云ing")
                console.log(this.cluster_list[index])
                console.log(res)
                const i = this.cluster_list[index]  //让词云图存储在对应的簇号作为下标的列表中
                this.wordCloud_list[i] = res
                this.imgLoading = false
              }
          )
        }
        //console.log("获取词云成功")
        // console.log(this.wordCloud_list)
        // console.log("cluster_list:"+this.cluster_list)
        // console.log("number_list+"+this.number_list)
      });
    },
    handleTree(){
      getClustersTreeView({ taskId: this.$route.params.taskId }).then(res => {
        console.log("获取后端树形结果")
        this.try_list = res
        console.log(this.try_list)
        this.toAString()
      })
    },

    toAString(){
      setTimeout(() => {
        console.log('延迟加载的内容toAString');
        this.try_list.forEach((item) => {
          item.id = item.id.toString()
          this.toTree()
        })
      }, 1000);
    },

    toTree(){
      setTimeout(() => {
        console.log('延迟加载的内容toTree');
        // * 先生成parent建立父子关系
        const obj = {};
        this.try_list.forEach((item) => {
          obj[item.id] = item;
        });
        // * obj -> {1001: {id: 1001, parentId: 0, name: 'AA'}, 1002: {...}}
        // console.log(obj, "obj")
        const res = [];
        this.try_list.forEach((item) => {
          const parent = obj[item.parentId];
          if (parent) {
            console.log("当前项有父节点")
            parent.children = parent.children || [];
            parent.children.push(item);
          } else {
            console.log("当前项没有父节点 -> 顶层")
            res.push(item);
          }
        });
        console.log("完成树形结构化")
        console.log(res[0])
        this.try_tree_date = res[0]
        // this.initTree()
      }, 2000);
    },


    initTree(){
      setTimeout(() => {
        console.log('延迟加载的内容initTree');
        const width = document.getElementById('container').scrollWidth || 800;
        const height = document.getElementById('container').scrollHeight || 800;
        const graph = new G6.TreeGraph({
          container: 'container',
          width,
          height,
          fitView: true,
          fitCenter: true,
          maxZoom: 1.5,
          linkCenter: true,
          //  设置画布的模式
          modes: {
            default: [
              {
                // 只适用于树图，展开或收起子树
                type: 'collapse-expand',
                // trigger：收起和展开节点分组的方式。支持 'click' 和 'dblclick' 两种方式。默认为 'dblclick'，即双击。
                trigger: 'click',
                // onChange：收起或展开的回调函数。警告：V3.1.2 版本中将移除；
                onChange: function onChange(item, collapsed) {
                  const data = item.get('model').data;
                  data.collapsed = collapsed;
                  return true;
                }
              },
              // 拖拽画布；
              'drag-canvas',
              // 缩放画布；
              'zoom-canvas',
            ]
          },

          // 默认状态下节点的配置，比如 type, size, color。会被写入的 data 覆盖。
          defaultNode: {
            type: 'circle',
            // size 是单个数字，表示直径
            size: 26,
            // 指定边连入节点的连接点的位置（相对于该节点而言），可以为空。例如: [0, 0]，代表节点左上角的锚点，[1, 1],代表节点右下角的锚点
            anchorPoints: [
              [0, 0.5],
              [1, 0.5],
            ],
            // 节点样式
            style: {
              // 节点填充色
              fill: '#C6E5FF',
              // 节点的描边颜色，''就是没颜色的意思
              stroke: '#5B8FF9',
              // 那个小手
              cursor:'pointer',
            },
            // Object 类型。配置标签文本
            labelCfg: {
              // 标签的样式属性。
              style: {
                // 文本颜色
                fill: '#000000',
                // 文本字体大小
                fontSize: 14,
              },
            },
          },
          // 默认状态下边的配置，比如 type, size, color。会被写入的 data 覆盖。
          defaultEdge: {
            // 指定边的类型，可以是内置边的类型名称，也可以是自定义边的名称。默认为 'line'
            type: 'cubic-vertical',
            // 边的样式属性
            style: {
              // 边的颜色
              stroke: '#A3B1BF',
            },
          },
          // 布局配置项，使用 type 字段指定使用的布局方式
          layout: {
            // 布局名称
            type: 'compactBox',
            // layout 的方向。
            direction: 'TB', // H / V / LR / RL / TB / BT
            // 下面的参数都是一个节点，当存在某些奇葩点节点的时候，可以通过以下控制
            // 节点 id 的回调函数
            getId: function getId(d) {
              return d.id;
            },
            // 节点高度的回调函数
            getHeight: function getHeight() {
              return 16;
            },
            // 节点宽度的回调函数
            getWidth: function getWidth() {
              return 16;
            },
            // 节点纵向间距的回调函数
            getVGap: function getVGap() {
              return 40;
            },
            // 节点横向间距的回调函数
            getHGap: function getHGap() {
              return 70;
            },
          },
          // 动画
          animate: true,
        });
        // 设置各个节点样式及其他配置，以及在各个状态下节点的 KeyShape 的样式
        // 该方法必须在 render 之前调用，否则不起作用
        // 使用 graph.node(nodeFn) 配置 > 数据中动态配置 > 实例化图时全局配置
        graph.node(function (node) {
          return {
            label: node.label,
            labelCfg: {
              position: node.children && node.children.length > 0 ? 'top' : 'bottom',
              offset: 5,
              style: {
                textAlign: 'center',
              },
            },
          };
        });
        // 初始化的图数据
        graph.data(this.try_tree_date);
        // 根据提供的数据渲染视图。
        graph.render();
        // 让画布内容适应视口
        graph.fitView();

        this.loading = false
      }, 3000);
    },

    // 跳转到每个报告详情查看页面
    handlePeek(reportID) {
      const tid = this.$route.params.taskId;
      this.$router.push(`/employer/post/${tid}/${reportID}`);
    },

    // 跳转到融合报告页面
    handleDetail(cluster){
      const tid = this.$route.params.taskId;
      this.$router.push(`/employer/aggregate/${tid}/${cluster}`)
    }
  },

  // mounted就是页面初始化的时候调用的函数
  mounted() {
    this.loadReports();
    // this.handleTree()
    // this.toAString()
    // this.toTree()
    // this.initTree()
  }
}
</script>

<style scoped>
.box {
  /*display:flex;*/
  /*justify-content:center;*/
  width: 80%;
  position:absolute;
  left:10%;
  /*top:50%;*/
  /*margin-left:-50px;*/
}
.aggregateReport-area{}
.aggregateReport{
  padding: 3px;
  margin-top: 30px;
  height: 100%;
}
.divider{
  margin-top: 5px;
  width: 100%;
  border: #000b82;
  box-shadow: 2px 2px 2px 2px #000b82;
}
.aggregateReport-trigger {
  cursor: pointer;
  color: #000b82;
  margin-left: 10px;
}
.g6-x {
  width: 100%;
  height: 400px;
  box-sizing: border-box;
  /*border: 1px solid #ccc;*/
  margin-left: 20px;
}
.picture{
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
}
.img{
  width: 200px;
  height: 200px;
}
</style>