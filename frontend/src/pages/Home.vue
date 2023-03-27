<template>
  <v-parallax
      dark
      height = "760"
      src="https://w.wallhaven.cc/full/yj/wallhaven-yjw53l.jpg"
  >
    <div>
      <v-container class="box">
        <v-row
            align="center"
            justify="center"
        >
          <v-col
              class="text-center"
              cols="12"
          >
            <h1 class="text-h4 font-weight-thin mb-4">
              COLLECT 众包测试系统
            </h1>
            <h4 class="subheading">
              欢迎使用！
            </h4>

            <div class="box">
              <div id="myChart" :style="{ width: '800px', height: '400px' }"></div>
            </div>

          </v-col>
        </v-row>
      </v-container>
    </div>
  </v-parallax>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Home",
  data() {
    return {
      msg: "fafa",
    };
  },
  mounted() {
    // this.drawLine();
    console.log(this.$echarts);
  },
  methods: {
    // drawLine() {
    //   // 基于准备好的dom，初始化echarts实例
    //   let myChart = this.$echarts.init(document.getElementById("myChart"));
    //   // 绘制图表
    //   myChart.setOption({
    //     title: {
    //       text: "我是echarts的主标题", //标题
    //       subtext: "我是echarts的副标题", //副标题
    //       x: "left", //可以设置标题的位置，有left、center、right三个
    //       textStyle: {
    //         color: "red", //设置标题的颜色
    //         fontSize: "18", //设置标题的大小
    //       },
    //     },
    //     tooltip: {
    //       tigger: "axis", //默认是item,当鼠标移动到图表上时显示当前节点的信息，如果为axis是显示这个横坐标范围内的所有节点信息;
    //       formatter: "{a}<br/>{b}:{c}({d}%)",
    //       axisPointer: {
    //         type: "cross", //设置鼠标移动到当前节点的会有虚线标识这个节点的横坐标和纵坐标，还有一个是shadow，显示的当前横坐标范围内的左右节点信息
    //         label: {
    //           backgroundColor: "green", //设置当前节点的横坐标与纵坐标跟随文字的背景颜色，突出了当前节点的坐标
    //         },
    //       },
    //     },
    //     grid: {
    //       //设置图标距离四周的距离
    //       x: 250, //x轴的位置
    //       y: 100, //y轴的高度
    //       x2: 60,
    //       y2: 70,
    //       containLabel: true, //
    //     },
    //     legend: {
    //       data: ["工人1", "工人2", "工人3", "工人4", "工人5"],
    //       //这个里边的没一个自动绑定series中对应的name的信息，这个是可以用来进行切换是否显示这个信息
    //       x: "center", //用来设定这些图例的位置,有left,center,right
    //       textStyle: {
    //         color: "yellow", //设置legend的颜色
    //         fontSize: "18", //设置legend的字体大小
    //       },
    //       itemHeight: 10, //高度
    //       itemWidth: 10, //宽度
    //       x: 50,
    //       y: 50,
    //       right: "50",
    //     },
    //     dataZoom: [
    //       //可实现底下多个滚动条，然后可左右滑动
    //       {
    //         show: true, //是否显示滚动条
    //         realtime: true,
    //         start: 0, //滚动条的起点位置
    //         end: 80, //滚动条的终止位置
    //         handleIcon:
    //             "M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4- 8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z", // 修改默认的滑块样式
    //         handleSize: "50%", //修改挂块的半径
    //         handleStyle: {
    //           color: "#fff", //修改滑块的颜色
    //           shadowBlur: 3,
    //           shadowColor: "rgba(0,0,0,0.6)",
    //           shadowOffsetX: 2, //X轴向右偏移的阴影
    //           shadowOffsetY: 2, //y轴向下偏移的阴影
    //         },
    //         bottom: "0%",
    //         height: "40",
    //       },
    //     ],
    //     toolbox: {
    //       left: "right", //设置先出图标操作的图标位置
    //       fearture: {
    //         dataZoom: {
    //           yAxisIndex: "none",
    //         },
    //         restore: {},
    //         saveAsimage: {},
    //       },
    //     },
    //     xAxis: {
    //       type: "category",
    //       boundaryGap: false, //图标的左边是否留白，false:不留，true留白
    //       data: ["安卓", "IOS", "鸿蒙", "Windows", "Linux", "三星"],
    //       axisLine: {
    //         symbol: ["none", "arrow"], //设置x轴或者y轴箭头
    //         lineStyle: {
    //           color: "#B5B5B5",
    //           width: 2, //这里是为了突出显示加上的
    //         }, //刻度是向内还是向外的
    //         axisTick: { inside: true, lengt: 1 },
    //         splitLine: {
    //           show: true,
    //           lineStyle: {
    //             type: "solid",
    //             color: "#FCFCFC", //网格颜色
    //           },
    //         },
    //         axisLabel: {
    //           //调整x轴的lable
    //           interval: 0, //按x轴的长度显示的刻度的个数，例如：本来显示20个刻度，因为x轴端只显示了10个
    //           totate: 0, //实现x轴的刻度的标识实现旋转，
    //           textStyle: {
    //             fontSize: 10, //让字体变大
    //             color: "#B5B5B5",
    //           },
    //         },
    //         nameTextStyle: {
    //           color: "#B5B5B5",
    //           verticalAlign: "bottom",
    //           fontSize: "10",
    //           padding: [35, 50, 10, -2], //设置x轴的标题的位置
    //         },
    //         boundaryGap: false,
    //       },
    //     },
    //     yAxis: [
    //       {
    //         type: "value",
    //         name: "高度(ml)", //设置左上角的单位指标
    //         axisLabel: {
    //           formatter: "{value}ml", //设置y轴坐标的单位
    //         },
    //         axisLine: {
    //           symbol: ["none", "arrow"], //设置x轴或y轴箭头
    //           lineStyle: {
    //             color: "pink", //左边刻度的颜色
    //             width: 2, //这里是为了突出显示加上的
    //           },
    //         }, //刻度是向内还是向外
    //         axisTick: {
    //           inside: false,
    //           length: 1,
    //         },
    //         splitLine: {
    //           show: true,
    //           lineStyle: {
    //             type: "solid",
    //             color: "green", //网格颜色
    //           },
    //         },
    //         asixLabel: {
    //           //调整x轴的lable
    //           interval: 0, //按x轴的长度显示的刻度的个数，例如：本来显示20个刻度，因为x轴端只显示了10个
    //           rotate: 0, //实现x轴的刻度的标识实现旋转，
    //           textStyle: {
    //             fontSize: 10, //让字体变大
    //             color: "red",
    //           },
    //         },
    //         nameTextStyle: {
    //           color: "blue",
    //           verticalAlign: "bottom",
    //           fontSize: "10",
    //           padding: [35, 50, 10, -2], //设置x轴的标题位置
    //         },
    //         boundaryGap: false,
    //       },
    //     ], //label的属性可放在外边也可以放在里边的series对应的内一个选项里面进行设置
    //     label: {
    //       //修改每个柱子所对应的数据
    //       normal: {
    //         show: true, //设置数据是否显示
    //         position: "inside", //设置显示的位置，有inside,outside,top,bottom
    //         textStyle: {
    //           fontSize: 15, //字体大小
    //           color: "red", //设置柱子上面数据的颜色
    //         },
    //       },
    //     },
    //
    //     series: [
    //       {
    //         name: "折现图1",
    //         type: "bar", //图的类型，还可以是line,
    //         stack: "总量",
    //         areaStyle: {
    //           normal: {
    //             color: "red", //设置填充的颜色，只针对折线图
    //           },
    //         },
    //         label: {
    //           normal: {
    //             show: true,
    //             position: "top", //设置显示在内还是在外
    //             textStyle: {
    //               fontSize: 15, //
    //               color: "red", //设置柱子上面数据的颜色
    //             },
    //           },
    //         },
    //         itemStyle: {
    //           //设置折线的颜色
    //           normal: {
    //             color: "blue", //图例颜色
    //             lineStyle: {
    //               color: "blue", //线条颜色
    //               width: 10, //线条粗细
    //             },
    //           },
    //         },
    //         data: [15, 20, 36, 10, 10, 20],
    //         //在柱状图的警戒线markLine中属性：
    //         markLine: {
    //           symbol: "none", //去掉警戒线最后面的箭头，
    //           label: {
    //             position: "start", //将警示值放在哪个位置，三个值start\middle\end
    //             fontSize: 18,
    //             formatterL: "69.5%",
    //           },
    //           barGap: "46", //增加柱间距
    //           data: [
    //             {
    //               sliendt: false, //鼠标悬停事件,true没有,false有
    //               lineStyle: {
    //                 type: "solid",
    //                 color: "red",
    //               },
    //               yAxis: 0.75, //警戒线的标注值，可以有多个yAxis,多条警示线，或者采用{type:'average',name:'平均值'}，type值有max min average，分为最大，最小,平均值
    //             },
    //           ],
    //         },
    //       },
    //     ],
    //   });
    // },
  },
}
</script>

<style scoped>
.box {
  margin-top: 50px;
}
</style>