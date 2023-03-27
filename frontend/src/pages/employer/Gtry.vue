<template>
  <div class="page-x">
    <!-- 【步骤1】 创建容器 -->
    <h2>树形图</h2>
    <!--    <div class="g6-x" id="containerG6" ref="containerG6"></div>-->
<!--    <div id="container"></div>-->

    <vue2-org-tree
        :data="try_tree_date"
        :horizontal="true"
        :label-class-name="labelClassName"
    />
  </div>
</template>

<script>
// 引入G6  ⭐main.js全局引入似乎没有作用
import G6 from '@antv/g6'
import { getClustersTreeView } from "@/api/report";

export default {
  name: "Gtry",
  data(){
    return{
      treeData : {
        id:'主报告',
        label:'主报告',
        children:[
          {
            id:'1.子报告',
            label:'1.子报告',
            children:[
              {
                id:'1.1子报告',
                label:'1.1子报告',
              },
              {
                id:'1.2子报告',
                label:'1.2子报告',
              }
            ]
          },
          {
            id:'2.子报告',
            label: '2.子报告',
            children: [
              {
                id:'2.1子报告',
                label: '2.1子报告',
              },
              {
                id:'2.2子报告',
                label: '2.2子报告',
              }
            ]
          }
        ]
      },
      new_list: [
        {
          id: 1006,
          parentId: 1002,
          label: "FF",
        },
        {
          id: 1002,
          parentId: 1001,
          label: "BB",
        },
        {
          id: 1001,
          parentId: 0,
          label: "AA",
        },
        {
          id: 1005,
          parentId: 1003,
          label: "EE",
        },
        {
          id: 1003,
          parentId: 1001,
          label: "CC",
        },
        {
          id: 1004,
          parentId: 1003,
          label: "DD",
        },

        {
          id: 1007,
          parentId: 1002,
          label: "GG",
        },
        {
          id: 1008,
          parentId: 1004,
          label: "HH",
        },
        {
          id: 1009,
          parentId: 1005,
          label: "II",
        },
      ],
      try_tree_date: {},
      tree_data: {},
      try_list : [],
      labelClassName:"bg-color-orange"
    }
  },
  methods: {
    handleTree(){
      getClustersTreeView({ taskId: 1 }).then(res => {
        console.log("获取后端树形结果")
        this.try_list = res
        console.log(this.try_list)
      })
    },

    toAString(){
      setTimeout(() => {
        console.log('延迟加载的内容toAString');
        this.try_list.forEach((item) => {
          item.id = item.id.toString()
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
      console.log(obj, "obj")
      const res = [];
      this.try_list.forEach((item) => {
        console.log("循环list")
        console.log(item.parentId)
        const parent = obj[item.parentId];
        console.log(parent)
        if (parent) {
          console.log("当前项有父节点")
          parent.children = parent.children || [];
          parent.children.push(item);
        } else {
          console.log("当前项没有父节点 -> 顶层")
          res.push(item);
        }
        console.log("----循环结束了啦啦啦啦啦啦啦啦----")
      });
      console.log(res[0])
      this.try_tree_date = res[0]
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
      }, 3000);
    },



    collapse(list) {
      var _this = this;
      list.forEach(function(child) {
        if (child.expand) {
          child.expand = false;
        }
        child.children && _this.collapse(child.children);
      });
    },
    onExpand(e,data) {
      if ("expand" in data) {
        data.expand = !data.expand;
        if (!data.expand && data.children) {
          this.collapse(data.children);
        }
      } else {
        this.$set(data, "expand", true);
      }
    },
    toggleExpand(data, val) {
      var _this = this;
      if (Array.isArray(data)) {
        data.forEach(function(item) {
          _this.$set(item, "expand", val);
          if (item.children) {
            _this.toggleExpand(item.children, val);
          }
        });
      } else {
        this.$set(data, "expand", val);
        if (data.children) {
          _this.toggleExpand(data.children, val);
        }
      }
    }
  },

  mounted() {
    this.handleTree()
    this.toAString()
    this.toTree()
    this.initTree()
    this.toggleExpand(this.try_tree_date,true)
  }
}
</script>

<style scoped>

.g6-x {
  width: 600px;
  height: 500px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  margin-left: 20px;
}
.container{
  width: 100%;
  height: 800px;
}
.bg-color-orange{
  color: #fff;
  background-color: orange;
}
</style>