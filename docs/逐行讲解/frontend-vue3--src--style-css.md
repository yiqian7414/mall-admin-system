# frontend-vue3/src/style.css

源文件：[`frontend-vue3/src/style.css`](../../商城管理系统/frontend-vue3/src/style.css)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>:root {</code> | 选择匹配 `:root` 的页面元素，开始为它们声明 CSS 样式。 |
| 2 | <code>  font-family: &quot;Microsoft YaHei&quot;, &quot;PingFang SC&quot;, system-ui, sans-serif;</code> | 指定字体及备用字体；本行取值为 `"Microsoft YaHei", "PingFang SC", system-ui, sans-serif`。 |
| 3 | <code>  color: #26354a;</code> | 设置文字颜色；本行取值为 `#26354a`。 |
| 4 | <code>  background: #f4f6fa;</code> | 设置背景；本行取值为 `#f4f6fa`。 |
| 5 | <code>  font-synthesis: none;</code> | 禁止浏览器合成不存在的粗体或斜体字形；本行取值为 `none`。 |
| 6 | <code>  --primary: #3267e3;</code> | 定义 CSS 自定义属性 --primary，后续可用 var(...) 复用这个设计值。 |
| 7 | <code>  --border: #e8edf4;</code> | 定义 CSS 自定义属性 --border，后续可用 var(...) 复用这个设计值。 |
| 8 | <code>  --muted: #8893a4;</code> | 定义 CSS 自定义属性 --muted，后续可用 var(...) 复用这个设计值。 |
| 9 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 10 | <code>.product-photo {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 11 | <code>  object-fit: cover;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 12 | <code>  flex-shrink: 0;</code> | 设置空间不足时的收缩比例；本行取值为 `0`。 |
| 13 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 14 | <code>.product-image-picker {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 15 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 16 | <code>  align-items: flex-start;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `flex-start`。 |
| 17 | <code>  flex-wrap: wrap;</code> | 设置弹性项目是否换行；本行取值为 `wrap`。 |
| 18 | <code>  gap: 16px;</code> | 设置布局项目之间的间距；本行取值为 `16px`。 |
| 19 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 20 | <code>.product-image-picker &gt; div {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 21 | <code>  flex: 1;</code> | 设置弹性项目的伸缩与基础尺寸；本行取值为 `1`。 |
| 22 | <code>  min-width: 200px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 23 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 24 | <code>.product-image-picker small {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 25 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 26 | <code>  margin: 8px 0;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 27 | <code>  color: #69778c;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 28 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 29 | <code>.image-preview {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 30 | <code>  width: 112px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 31 | <code>  height: 112px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 32 | <code>  object-fit: contain;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 33 | <code>  border: 1px solid #e8edf4;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 34 | <code>  border-radius: 8px;</code> | 设置圆角半径；本行取值为 `8px`。 |
| 35 | <code>  background: #f4f6fa;</code> | 设置背景；本行取值为 `#f4f6fa`。 |
| 36 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 37 | <code>.inventory-notice {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 38 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 39 | <code>  flex-wrap: wrap;</code> | 设置弹性项目是否换行；本行取值为 `wrap`。 |
| 40 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 41 | <code>  gap: 12px;</code> | 设置布局项目之间的间距；本行取值为 `12px`。 |
| 42 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 43 | <code>  padding: 14px 16px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 44 | <code>  margin: 16px 0;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 45 | <code>  background: #edf3ff;</code> | 设置背景；本行取值为 `#edf3ff`。 |
| 46 | <code>  border-radius: 8px;</code> | 设置圆角半径；本行取值为 `8px`。 |
| 47 | <code>  color: #294b91;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 48 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 49 | <code>.inventory-notice a {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 50 | <code>  color: #3267e3;</code> | 设置文字颜色；本行取值为 `#3267e3`。 |
| 51 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 52 | <code>.inventory-actions {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 53 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 54 | <code>  flex-wrap: wrap;</code> | 设置弹性项目是否换行；本行取值为 `wrap`。 |
| 55 | <code>  gap: 10px;</code> | 设置布局项目之间的间距；本行取值为 `10px`。 |
| 56 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 57 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 58 | <code>.inventory-actions input {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 59 | <code>  flex: 1;</code> | 设置弹性项目的伸缩与基础尺寸；本行取值为 `1`。 |
| 60 | <code>  min-width: 120px;</code> | 限制最小宽度；本行取值为 `120px`。 |
| 61 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 62 | <code>.inventory-tabs {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 63 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 64 | <code>  gap: 20px;</code> | 设置布局项目之间的间距；本行取值为 `20px`。 |
| 65 | <code>  padding: 0 24px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 66 | <code>  border-bottom: 1px solid #e8edf4;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 67 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 68 | <code>.inventory-tabs button {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 69 | <code>  padding: 14px 2px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 70 | <code>  border: 0;</code> | 设置边框宽度、样式和颜色；本行取值为 `0`。 |
| 71 | <code>  border-bottom: 2px solid transparent;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 72 | <code>  background: transparent;</code> | 设置背景；本行取值为 `transparent`。 |
| 73 | <code>  color: #69778c;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 74 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 75 | <code>.inventory-tabs button.selected {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 76 | <code>  color: #3267e3;</code> | 设置文字颜色；本行取值为 `#3267e3`。 |
| 77 | <code>  border-bottom-color: #3267e3;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 78 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 79 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 80 | <code>.inventory-filters {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 81 | <code>  flex-wrap: wrap;</code> | 设置弹性项目是否换行；本行取值为 `wrap`。 |
| 82 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 83 | <code>.inventory-filters input {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 84 | <code>  width: 180px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 85 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 86 | <code>.inventory-filters select {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 87 | <code>  width: 150px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 88 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 89 | <code>.movement-table {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 90 | <code>  min-width: 1120px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 91 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 92 | <code>.movement-table th,</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 93 | <code>.movement-table td {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 94 | <code>  padding-left: 16px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 95 | <code>  padding-right: 16px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 96 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 97 | <code>.movement-table td:nth-child(2),</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 98 | <code>.movement-table td:nth-child(3),</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 99 | <code>.movement-table td:nth-child(4),</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 100 | <code>.movement-table td:nth-child(5),</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 101 | <code>.movement-table td:nth-child(8),</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 102 | <code>.movement-table td:nth-child(9) {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 103 | <code>  white-space: nowrap;</code> | 设置空白和换行处理方式；本行取值为 `nowrap`。 |
| 104 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 105 | <code>.movement-table td:first-child {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 106 | <code>  min-width: 150px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 107 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 108 | <code>.filter-bar.inventory-filters {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 109 | <code>  padding-top: 18px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 110 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 111 | <code>.movement-reference {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 112 | <code>  max-width: 210px;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 113 | <code>  overflow-wrap: anywhere;</code> | 允许过长内容在必要时换行；本行取值为 `anywhere`。 |
| 114 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 115 | <code>.movement-delta {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 116 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 117 | <code>  font-variant-numeric: tabular-nums;</code> | 为数字启用等宽等排版特性，方便金额/数量对齐；本行取值为 `tabular-nums`。 |
| 118 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 119 | <code>.stock-in {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 120 | <code>  color: #08795d;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 121 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 122 | <code>.stock-out {</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 123 | <code>  color: #c73c4b;</code> | 定义当前选择器下的样式属性，控制布局、间距、颜色或响应式显示，不修改业务数据。 |
| 124 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 125 | <code>* {</code> | 块注释中的说明：{；不参与运行。 |
| 126 | <code>  box-sizing: border-box;</code> | 指定宽高是否包含边框和内边距；本行取值为 `border-box`。 |
| 127 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 128 | <code>body {</code> | 选择匹配 `body` 的页面元素，开始为它们声明 CSS 样式。 |
| 129 | <code>  margin: 0;</code> | 设置元素外侧留白；本行取值为 `0`。 |
| 130 | <code>  font-size: 14px;</code> | 设置字号；本行取值为 `14px`。 |
| 131 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 132 | <code>button,</code> | 并列 CSS 选择器 `button`：与后续选择器共用同一组样式。 |
| 133 | <code>input,</code> | 并列 CSS 选择器 `input`：与后续选择器共用同一组样式。 |
| 134 | <code>select,</code> | 并列 CSS 选择器 `select`：与后续选择器共用同一组样式。 |
| 135 | <code>textarea {</code> | 选择匹配 `textarea` 的页面元素，开始为它们声明 CSS 样式。 |
| 136 | <code>  font: inherit;</code> | 设置字体相关属性；本行取值为 `inherit`。 |
| 137 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 138 | <code>button,</code> | 并列 CSS 选择器 `button`：与后续选择器共用同一组样式。 |
| 139 | <code>a,</code> | 并列 CSS 选择器 `a`：与后续选择器共用同一组样式。 |
| 140 | <code>input,</code> | 并列 CSS 选择器 `input`：与后续选择器共用同一组样式。 |
| 141 | <code>select,</code> | 并列 CSS 选择器 `select`：与后续选择器共用同一组样式。 |
| 142 | <code>textarea {</code> | 选择匹配 `textarea` 的页面元素，开始为它们声明 CSS 样式。 |
| 143 | <code>  -webkit-tap-highlight-color: transparent;</code> | 设置移动浏览器点击元素的高亮色；本行取值为 `transparent`。 |
| 144 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 145 | <code>button {</code> | 选择匹配 `button` 的页面元素，开始为它们声明 CSS 样式。 |
| 146 | <code>  cursor: pointer;</code> | 设置鼠标指针样式；本行取值为 `pointer`。 |
| 147 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 148 | <code>button:disabled {</code> | 选择匹配 `button:disabled` 的页面元素，开始为它们声明 CSS 样式。 |
| 149 | <code>  opacity: 0.5;</code> | 设置不透明度；本行取值为 `0.5`。 |
| 150 | <code>  cursor: not-allowed;</code> | 设置鼠标指针样式；本行取值为 `not-allowed`。 |
| 151 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 152 | <code>a {</code> | 选择匹配 `a` 的页面元素，开始为它们声明 CSS 样式。 |
| 153 | <code>  color: inherit;</code> | 设置文字颜色；本行取值为 `inherit`。 |
| 154 | <code>  text-decoration: none;</code> | 设置文字装饰；本行取值为 `none`。 |
| 155 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 156 | <code>h1,</code> | 并列 CSS 选择器 `h1`：与后续选择器共用同一组样式。 |
| 157 | <code>h2,</code> | 并列 CSS 选择器 `h2`：与后续选择器共用同一组样式。 |
| 158 | <code>h3,</code> | 并列 CSS 选择器 `h3`：与后续选择器共用同一组样式。 |
| 159 | <code>p {</code> | 选择匹配 `p` 的页面元素，开始为它们声明 CSS 样式。 |
| 160 | <code>  margin-top: 0;</code> | 设置上方外边距；本行取值为 `0`。 |
| 161 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 162 | <code>h1 {</code> | 选择匹配 `h1` 的页面元素，开始为它们声明 CSS 样式。 |
| 163 | <code>  margin-bottom: 8px;</code> | 设置下方外边距；本行取值为 `8px`。 |
| 164 | <code>  font-size: 25px;</code> | 设置字号；本行取值为 `25px`。 |
| 165 | <code>  letter-spacing: -0.5px;</code> | 设置字符间距；本行取值为 `-0.5px`。 |
| 166 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 167 | <code>h2 {</code> | 选择匹配 `h2` 的页面元素，开始为它们声明 CSS 样式。 |
| 168 | <code>  margin-bottom: 6px;</code> | 设置下方外边距；本行取值为 `6px`。 |
| 169 | <code>  font-size: 16px;</code> | 设置字号；本行取值为 `16px`。 |
| 170 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 171 | <code>h3 {</code> | 选择匹配 `h3` 的页面元素，开始为它们声明 CSS 样式。 |
| 172 | <code>  font-size: 15px;</code> | 设置字号；本行取值为 `15px`。 |
| 173 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 174 | <code>p {</code> | 选择匹配 `p` 的页面元素，开始为它们声明 CSS 样式。 |
| 175 | <code>  line-height: 1.7;</code> | 设置行高；本行取值为 `1.7`。 |
| 176 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 177 | <code>small {</code> | 选择匹配 `small` 的页面元素，开始为它们声明 CSS 样式。 |
| 178 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 179 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 180 | <code>button:focus-visible,</code> | 并列 CSS 选择器 `button:focus-visible`：与后续选择器共用同一组样式。 |
| 181 | <code>a:focus-visible {</code> | 选择匹配 `a:focus-visible` 的页面元素，开始为它们声明 CSS 样式。 |
| 182 | <code>  outline: 3px solid #9bb7ff;</code> | 设置不占据布局空间的外轮廓；本行取值为 `3px solid #9bb7ff`。 |
| 183 | <code>  outline-offset: 3px;</code> | 设置外轮廓与边框之间的距离；本行取值为 `3px`。 |
| 184 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 185 | <code>input,</code> | 并列 CSS 选择器 `input`：与后续选择器共用同一组样式。 |
| 186 | <code>select,</code> | 并列 CSS 选择器 `select`：与后续选择器共用同一组样式。 |
| 187 | <code>textarea {</code> | 选择匹配 `textarea` 的页面元素，开始为它们声明 CSS 样式。 |
| 188 | <code>  width: 100%;</code> | 设置宽度；本行取值为 `100%`。 |
| 189 | <code>  border: 1px solid #dfe5ee;</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid #dfe5ee`。 |
| 190 | <code>  border-radius: 6px;</code> | 设置圆角半径；本行取值为 `6px`。 |
| 191 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 192 | <code>  padding: 10px 12px;</code> | 设置内容与边框之间的内边距；本行取值为 `10px 12px`。 |
| 193 | <code>  outline: none;</code> | 设置不占据布局空间的外轮廓；本行取值为 `none`。 |
| 194 | <code>  color: #26354a;</code> | 设置文字颜色；本行取值为 `#26354a`。 |
| 195 | <code>  transition:</code> | 开始多行过渡效果声明，后续列出参与过渡的 CSS 属性与时长。 |
| 196 | <code>    border-color 0.15s,</code> | 并列 CSS 选择器 `border-color 0.15s`：与后续选择器共用同一组样式。 |
| 197 | <code>    box-shadow 0.15s;</code> | 为 `box-shadow` 属性设置 0.15s 过渡，使聚焦/悬停变化平滑。 |
| 198 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 199 | <code>input:focus,</code> | 并列 CSS 选择器 `input:focus`：与后续选择器共用同一组样式。 |
| 200 | <code>select:focus,</code> | 并列 CSS 选择器 `select:focus`：与后续选择器共用同一组样式。 |
| 201 | <code>textarea:focus {</code> | 选择匹配 `textarea:focus` 的页面元素，开始为它们声明 CSS 样式。 |
| 202 | <code>  border-color: #7a9df0;</code> | 设置边框颜色；本行取值为 `#7a9df0`。 |
| 203 | <code>  box-shadow: 0 0 0 3px #3267e312;</code> | 为元素添加阴影；本行取值为 `0 0 0 3px #3267e312`。 |
| 204 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 205 | <code>input::placeholder,</code> | 并列 CSS 选择器 `input::placeholder`：与后续选择器共用同一组样式。 |
| 206 | <code>textarea::placeholder {</code> | 选择匹配 `textarea::placeholder` 的页面元素，开始为它们声明 CSS 样式。 |
| 207 | <code>  color: #a5afbd;</code> | 设置文字颜色；本行取值为 `#a5afbd`。 |
| 208 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 209 | <code>input:disabled,</code> | 并列 CSS 选择器 `input:disabled`：与后续选择器共用同一组样式。 |
| 210 | <code>select:disabled {</code> | 选择匹配 `select:disabled` 的页面元素，开始为它们声明 CSS 样式。 |
| 211 | <code>  color: #8c96a6;</code> | 设置文字颜色；本行取值为 `#8c96a6`。 |
| 212 | <code>  background: #f4f6fa;</code> | 设置背景；本行取值为 `#f4f6fa`。 |
| 213 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 214 | <code>textarea {</code> | 选择匹配 `textarea` 的页面元素，开始为它们声明 CSS 样式。 |
| 215 | <code>  resize: vertical;</code> | 设置输入控件是否可调整尺寸；本行取值为 `vertical`。 |
| 216 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 217 | <code>select {</code> | 选择匹配 `select` 的页面元素，开始为它们声明 CSS 样式。 |
| 218 | <code>  min-width: 120px;</code> | 限制最小宽度；本行取值为 `120px`。 |
| 219 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 220 | <code>.muted {</code> | 选择匹配 `.muted` 的页面元素，开始为它们声明 CSS 样式。 |
| 221 | <code>  color: var(--muted);</code> | 设置文字颜色；本行取值为 `var(--muted)`。 |
| 222 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 223 | <code>.nowrap {</code> | 选择匹配 `.nowrap` 的页面元素，开始为它们声明 CSS 样式。 |
| 224 | <code>  white-space: nowrap;</code> | 设置空白和换行处理方式；本行取值为 `nowrap`。 |
| 225 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 226 | <code>.button {</code> | 选择匹配 `.button` 的页面元素，开始为它们声明 CSS 样式。 |
| 227 | <code>  display: inline-flex;</code> | 设置布局方式；本行取值为 `inline-flex`。 |
| 228 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 229 | <code>  justify-content: center;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `center`。 |
| 230 | <code>  gap: 10px;</code> | 设置布局项目之间的间距；本行取值为 `10px`。 |
| 231 | <code>  padding: 10px 18px;</code> | 设置内容与边框之间的内边距；本行取值为 `10px 18px`。 |
| 232 | <code>  border: 1px solid var(--primary);</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid var(--primary)`。 |
| 233 | <code>  border-radius: 6px;</code> | 设置圆角半径；本行取值为 `6px`。 |
| 234 | <code>  background: var(--primary);</code> | 设置背景；本行取值为 `var(--primary)`。 |
| 235 | <code>  color: #fff;</code> | 设置文字颜色；本行取值为 `#fff`。 |
| 236 | <code>  font-weight: 500;</code> | 设置字重；本行取值为 `500`。 |
| 237 | <code>  white-space: nowrap;</code> | 设置空白和换行处理方式；本行取值为 `nowrap`。 |
| 238 | <code>  transition: background 0.15s;</code> | 设置属性变化时的过渡效果；本行取值为 `background 0.15s`。 |
| 239 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 240 | <code>.button:hover:not(:disabled) {</code> | 选择匹配 `.button:hover:not(:disabled)` 的页面元素，开始为它们声明 CSS 样式。 |
| 241 | <code>  background: #2357cb;</code> | 设置背景；本行取值为 `#2357cb`。 |
| 242 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 243 | <code>.button.secondary {</code> | 选择匹配 `.button.secondary` 的页面元素，开始为它们声明 CSS 样式。 |
| 244 | <code>  border-color: #dfe5ee;</code> | 设置边框颜色；本行取值为 `#dfe5ee`。 |
| 245 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 246 | <code>  color: #526078;</code> | 设置文字颜色；本行取值为 `#526078`。 |
| 247 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 248 | <code>.button.secondary:hover:not(:disabled) {</code> | 选择匹配 `.button.secondary:hover:not(:disabled)` 的页面元素，开始为它们声明 CSS 样式。 |
| 249 | <code>  background: #f3f6fb;</code> | 设置背景；本行取值为 `#f3f6fb`。 |
| 250 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 251 | <code>.button.small {</code> | 选择匹配 `.button.small` 的页面元素，开始为它们声明 CSS 样式。 |
| 252 | <code>  padding: 7px 12px;</code> | 设置内容与边框之间的内边距；本行取值为 `7px 12px`。 |
| 253 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 254 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 255 | <code>.button.danger {</code> | 选择匹配 `.button.danger` 的页面元素，开始为它们声明 CSS 样式。 |
| 256 | <code>  border-color: #df5360;</code> | 设置边框颜色；本行取值为 `#df5360`。 |
| 257 | <code>  background: #df5360;</code> | 设置背景；本行取值为 `#df5360`。 |
| 258 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 259 | <code>.button.danger-outline {</code> | 选择匹配 `.button.danger-outline` 的页面元素，开始为它们声明 CSS 样式。 |
| 260 | <code>  border-color: #f0ccd0;</code> | 设置边框颜色；本行取值为 `#f0ccd0`。 |
| 261 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 262 | <code>  color: #d34e5b;</code> | 设置文字颜色；本行取值为 `#d34e5b`。 |
| 263 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 264 | <code>.button.danger-outline:hover {</code> | 选择匹配 `.button.danger-outline:hover` 的页面元素，开始为它们声明 CSS 样式。 |
| 265 | <code>  background: #fff4f5;</code> | 设置背景；本行取值为 `#fff4f5`。 |
| 266 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 267 | <code>.icon-button {</code> | 选择匹配 `.icon-button` 的页面元素，开始为它们声明 CSS 样式。 |
| 268 | <code>  display: inline-flex;</code> | 设置布局方式；本行取值为 `inline-flex`。 |
| 269 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 270 | <code>  justify-content: center;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `center`。 |
| 271 | <code>  width: 32px;</code> | 设置宽度；本行取值为 `32px`。 |
| 272 | <code>  height: 32px;</code> | 设置高度；本行取值为 `32px`。 |
| 273 | <code>  background: transparent;</code> | 设置背景；本行取值为 `transparent`。 |
| 274 | <code>  border: 0;</code> | 设置边框宽度、样式和颜色；本行取值为 `0`。 |
| 275 | <code>  border-radius: 5px;</code> | 设置圆角半径；本行取值为 `5px`。 |
| 276 | <code>  color: #8290a6;</code> | 设置文字颜色；本行取值为 `#8290a6`。 |
| 277 | <code>  font-size: 23px;</code> | 设置字号；本行取值为 `23px`。 |
| 278 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 279 | <code>.icon-button:hover {</code> | 选择匹配 `.icon-button:hover` 的页面元素，开始为它们声明 CSS 样式。 |
| 280 | <code>  background: #f0f3f8;</code> | 设置背景；本行取值为 `#f0f3f8`。 |
| 281 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 282 | <code>.text-link,</code> | 并列 CSS 选择器 `.text-link`：与后续选择器共用同一组样式。 |
| 283 | <code>.link-button {</code> | 选择匹配 `.link-button` 的页面元素，开始为它们声明 CSS 样式。 |
| 284 | <code>  background: transparent;</code> | 设置背景；本行取值为 `transparent`。 |
| 285 | <code>  border: 0;</code> | 设置边框宽度、样式和颜色；本行取值为 `0`。 |
| 286 | <code>  padding: 0;</code> | 设置内容与边框之间的内边距；本行取值为 `0`。 |
| 287 | <code>  color: var(--primary);</code> | 设置文字颜色；本行取值为 `var(--primary)`。 |
| 288 | <code>  font-size: 13px;</code> | 设置字号；本行取值为 `13px`。 |
| 289 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 290 | <code>.danger-text {</code> | 选择匹配 `.danger-text` 的页面元素，开始为它们声明 CSS 样式。 |
| 291 | <code>  color: #d65463 !important;</code> | 设置文字颜色；本行取值为 `#d65463 !important`。 |
| 292 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 293 | <code>.admin-shell {</code> | 选择匹配 `.admin-shell` 的页面元素，开始为它们声明 CSS 样式。 |
| 294 | <code>  min-height: 100vh;</code> | 限制最小高度；本行取值为 `100vh`。 |
| 295 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 296 | <code>.sidebar {</code> | 选择匹配 `.sidebar` 的页面元素，开始为它们声明 CSS 样式。 |
| 297 | <code>  position: fixed;</code> | 设置定位方式；本行取值为 `fixed`。 |
| 298 | <code>  inset: 0 auto 0 0;</code> | 同时设置四边定位距离；本行取值为 `0 auto 0 0`。 |
| 299 | <code>  z-index: 30;</code> | 设置定位元素的层叠顺序；本行取值为 `30`。 |
| 300 | <code>  width: 228px;</code> | 设置宽度；本行取值为 `228px`。 |
| 301 | <code>  padding: 30px 18px;</code> | 设置内容与边框之间的内边距；本行取值为 `30px 18px`。 |
| 302 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 303 | <code>  border-right: 1px solid var(--border);</code> | 设置右边框；本行取值为 `1px solid var(--border)`。 |
| 304 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 305 | <code>.brand {</code> | 选择匹配 `.brand` 的页面元素，开始为它们声明 CSS 样式。 |
| 306 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 307 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 308 | <code>  gap: 12px;</code> | 设置布局项目之间的间距；本行取值为 `12px`。 |
| 309 | <code>  padding: 0 10px;</code> | 设置内容与边框之间的内边距；本行取值为 `0 10px`。 |
| 310 | <code>  font-size: 19px;</code> | 设置字号；本行取值为 `19px`。 |
| 311 | <code>  font-weight: 700;</code> | 设置字重；本行取值为 `700`。 |
| 312 | <code>  letter-spacing: 0.5px;</code> | 设置字符间距；本行取值为 `0.5px`。 |
| 313 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 314 | <code>.brand small {</code> | 选择匹配 `.brand small` 的页面元素，开始为它们声明 CSS 样式。 |
| 315 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 316 | <code>  margin-top: 5px;</code> | 设置上方外边距；本行取值为 `5px`。 |
| 317 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 318 | <code>  font-weight: 400;</code> | 设置字重；本行取值为 `400`。 |
| 319 | <code>  color: #8d99ac;</code> | 设置文字颜色；本行取值为 `#8d99ac`。 |
| 320 | <code>  letter-spacing: 1px;</code> | 设置字符间距；本行取值为 `1px`。 |
| 321 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 322 | <code>.brand-symbol {</code> | 选择匹配 `.brand-symbol` 的页面元素，开始为它们声明 CSS 样式。 |
| 323 | <code>  display: inline-flex;</code> | 设置布局方式；本行取值为 `inline-flex`。 |
| 324 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 325 | <code>  justify-content: center;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `center`。 |
| 326 | <code>  flex-shrink: 0;</code> | 设置空间不足时的收缩比例；本行取值为 `0`。 |
| 327 | <code>  width: 39px;</code> | 设置宽度；本行取值为 `39px`。 |
| 328 | <code>  height: 39px;</code> | 设置高度；本行取值为 `39px`。 |
| 329 | <code>  border-radius: 12px;</code> | 设置圆角半径；本行取值为 `12px`。 |
| 330 | <code>  background: #3267e3;</code> | 设置背景；本行取值为 `#3267e3`。 |
| 331 | <code>  color: #fff;</code> | 设置文字颜色；本行取值为 `#fff`。 |
| 332 | <code>  font-size: 24px;</code> | 设置字号；本行取值为 `24px`。 |
| 333 | <code>  font-weight: 800;</code> | 设置字重；本行取值为 `800`。 |
| 334 | <code>  font-family: Arial, sans-serif;</code> | 指定字体及备用字体；本行取值为 `Arial, sans-serif`。 |
| 335 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 336 | <code>.nav-caption {</code> | 选择匹配 `.nav-caption` 的页面元素，开始为它们声明 CSS 样式。 |
| 337 | <code>  margin: 45px 17px 14px;</code> | 设置元素外侧留白；本行取值为 `45px 17px 14px`。 |
| 338 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 339 | <code>  letter-spacing: 2px;</code> | 设置字符间距；本行取值为 `2px`。 |
| 340 | <code>  color: #a5adba;</code> | 设置文字颜色；本行取值为 `#a5adba`。 |
| 341 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 342 | <code>.sidebar nav {</code> | 选择匹配 `.sidebar nav` 的页面元素，开始为它们声明 CSS 样式。 |
| 343 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 344 | <code>  flex-direction: column;</code> | 设置弹性布局排列方向；本行取值为 `column`。 |
| 345 | <code>  gap: 7px;</code> | 设置布局项目之间的间距；本行取值为 `7px`。 |
| 346 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 347 | <code>.sidebar nav a {</code> | 选择匹配 `.sidebar nav a` 的页面元素，开始为它们声明 CSS 样式。 |
| 348 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 349 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 350 | <code>  gap: 14px;</code> | 设置布局项目之间的间距；本行取值为 `14px`。 |
| 351 | <code>  padding: 14px 17px;</code> | 设置内容与边框之间的内边距；本行取值为 `14px 17px`。 |
| 352 | <code>  color: #707d91;</code> | 设置文字颜色；本行取值为 `#707d91`。 |
| 353 | <code>  border-radius: 7px;</code> | 设置圆角半径；本行取值为 `7px`。 |
| 354 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 355 | <code>.sidebar nav a:hover {</code> | 选择匹配 `.sidebar nav a:hover` 的页面元素，开始为它们声明 CSS 样式。 |
| 356 | <code>  background: #f6f8fc;</code> | 设置背景；本行取值为 `#f6f8fc`。 |
| 357 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 358 | <code>.sidebar nav a.active {</code> | 选择匹配 `.sidebar nav a.active` 的页面元素，开始为它们声明 CSS 样式。 |
| 359 | <code>  background: #edf3ff;</code> | 设置背景；本行取值为 `#edf3ff`。 |
| 360 | <code>  color: #3267e3;</code> | 设置文字颜色；本行取值为 `#3267e3`。 |
| 361 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 362 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 363 | <code>.nav-symbol {</code> | 选择匹配 `.nav-symbol` 的页面元素，开始为它们声明 CSS 样式。 |
| 364 | <code>  width: 19px;</code> | 设置宽度；本行取值为 `19px`。 |
| 365 | <code>  font-size: 21px;</code> | 设置字号；本行取值为 `21px`。 |
| 366 | <code>  line-height: 1;</code> | 设置行高；本行取值为 `1`。 |
| 367 | <code>  text-align: center;</code> | 设置行内内容水平对齐；本行取值为 `center`。 |
| 368 | <code>  font-weight: 400;</code> | 设置字重；本行取值为 `400`。 |
| 369 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 370 | <code>.sidebar-footer {</code> | 选择匹配 `.sidebar-footer` 的页面元素，开始为它们声明 CSS 样式。 |
| 371 | <code>  position: absolute;</code> | 设置定位方式；本行取值为 `absolute`。 |
| 372 | <code>  bottom: 30px;</code> | 设置底部定位距离；本行取值为 `30px`。 |
| 373 | <code>  left: 30px;</code> | 设置左侧定位距离；本行取值为 `30px`。 |
| 374 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 375 | <code>  color: #8793a6;</code> | 设置文字颜色；本行取值为 `#8793a6`。 |
| 376 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 377 | <code>.sidebar-footer small {</code> | 选择匹配 `.sidebar-footer small` 的页面元素，开始为它们声明 CSS 样式。 |
| 378 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 379 | <code>  margin: 7px 0 0 14px;</code> | 设置元素外侧留白；本行取值为 `7px 0 0 14px`。 |
| 380 | <code>  color: #a3adbb;</code> | 设置文字颜色；本行取值为 `#a3adbb`。 |
| 381 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 382 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 383 | <code>.online-dot {</code> | 选择匹配 `.online-dot` 的页面元素，开始为它们声明 CSS 样式。 |
| 384 | <code>  display: inline-block;</code> | 设置布局方式；本行取值为 `inline-block`。 |
| 385 | <code>  height: 6px;</code> | 设置高度；本行取值为 `6px`。 |
| 386 | <code>  width: 6px;</code> | 设置宽度；本行取值为 `6px`。 |
| 387 | <code>  margin-right: 7px;</code> | 设置右侧外边距；本行取值为 `7px`。 |
| 388 | <code>  border-radius: 50%;</code> | 设置圆角半径；本行取值为 `50%`。 |
| 389 | <code>  background: #43b394;</code> | 设置背景；本行取值为 `#43b394`。 |
| 390 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 391 | <code>.workspace {</code> | 选择匹配 `.workspace` 的页面元素，开始为它们声明 CSS 样式。 |
| 392 | <code>  margin-left: 228px;</code> | 设置左侧外边距；本行取值为 `228px`。 |
| 393 | <code>  min-height: 100vh;</code> | 限制最小高度；本行取值为 `100vh`。 |
| 394 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 395 | <code>.topbar {</code> | 选择匹配 `.topbar` 的页面元素，开始为它们声明 CSS 样式。 |
| 396 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 397 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 398 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 399 | <code>  height: 78px;</code> | 设置高度；本行取值为 `78px`。 |
| 400 | <code>  padding: 0 36px;</code> | 设置内容与边框之间的内边距；本行取值为 `0 36px`。 |
| 401 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 402 | <code>  border-bottom: 1px solid var(--border);</code> | 设置下边框；本行取值为 `1px solid var(--border)`。 |
| 403 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 404 | <code>.breadcrumb {</code> | 选择匹配 `.breadcrumb` 的页面元素，开始为它们声明 CSS 样式。 |
| 405 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 406 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 407 | <code>  gap: 16px;</code> | 设置布局项目之间的间距；本行取值为 `16px`。 |
| 408 | <code>  color: #95a0b0;</code> | 设置文字颜色；本行取值为 `#95a0b0`。 |
| 409 | <code>  font-size: 13px;</code> | 设置字号；本行取值为 `13px`。 |
| 410 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 411 | <code>.breadcrumb strong {</code> | 选择匹配 `.breadcrumb strong` 的页面元素，开始为它们声明 CSS 样式。 |
| 412 | <code>  font-weight: 500;</code> | 设置字重；本行取值为 `500`。 |
| 413 | <code>  color: #45546a;</code> | 设置文字颜色；本行取值为 `#45546a`。 |
| 414 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 415 | <code>.crumb-divider {</code> | 选择匹配 `.crumb-divider` 的页面元素，开始为它们声明 CSS 样式。 |
| 416 | <code>  color: #ccd3dd;</code> | 设置文字颜色；本行取值为 `#ccd3dd`。 |
| 417 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 418 | <code>.account {</code> | 选择匹配 `.account` 的页面元素，开始为它们声明 CSS 样式。 |
| 419 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 420 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 421 | <code>  gap: 11px;</code> | 设置布局项目之间的间距；本行取值为 `11px`。 |
| 422 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 423 | <code>.avatar {</code> | 选择匹配 `.avatar` 的页面元素，开始为它们声明 CSS 样式。 |
| 424 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 425 | <code>  place-items: center;</code> | 同时设置网格项目的水平与垂直对齐；本行取值为 `center`。 |
| 426 | <code>  height: 34px;</code> | 设置高度；本行取值为 `34px`。 |
| 427 | <code>  width: 34px;</code> | 设置宽度；本行取值为 `34px`。 |
| 428 | <code>  background: #eaf0fe;</code> | 设置背景；本行取值为 `#eaf0fe`。 |
| 429 | <code>  color: #5076cb;</code> | 设置文字颜色；本行取值为 `#5076cb`。 |
| 430 | <code>  border-radius: 50%;</code> | 设置圆角半径；本行取值为 `50%`。 |
| 431 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 432 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 433 | <code>.account strong {</code> | 选择匹配 `.account strong` 的页面元素，开始为它们声明 CSS 样式。 |
| 434 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 435 | <code>  font-weight: 500;</code> | 设置字重；本行取值为 `500`。 |
| 436 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 437 | <code>.account small {</code> | 选择匹配 `.account small` 的页面元素，开始为它们声明 CSS 样式。 |
| 438 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 439 | <code>  margin-top: 3px;</code> | 设置上方外边距；本行取值为 `3px`。 |
| 440 | <code>  color: #9aa5b6;</code> | 设置文字颜色；本行取值为 `#9aa5b6`。 |
| 441 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 442 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 443 | <code>.logout-button {</code> | 选择匹配 `.logout-button` 的页面元素，开始为它们声明 CSS 样式。 |
| 444 | <code>  margin-left: 12px;</code> | 设置左侧外边距；本行取值为 `12px`。 |
| 445 | <code>  padding: 5px 0 5px 18px;</code> | 设置内容与边框之间的内边距；本行取值为 `5px 0 5px 18px`。 |
| 446 | <code>  border: 0;</code> | 设置边框宽度、样式和颜色；本行取值为 `0`。 |
| 447 | <code>  border-left: 1px solid var(--border);</code> | 设置左边框；本行取值为 `1px solid var(--border)`。 |
| 448 | <code>  background: transparent;</code> | 设置背景；本行取值为 `transparent`。 |
| 449 | <code>  color: #7b8799;</code> | 设置文字颜色；本行取值为 `#7b8799`。 |
| 450 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 451 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 452 | <code>.page-content {</code> | 选择匹配 `.page-content` 的页面元素，开始为它们声明 CSS 样式。 |
| 453 | <code>  padding: 34px 36px 24px;</code> | 设置内容与边框之间的内边距；本行取值为 `34px 36px 24px`。 |
| 454 | <code>  max-width: 1680px;</code> | 限制最大宽度；本行取值为 `1680px`。 |
| 455 | <code>  margin: 0 auto;</code> | 设置元素外侧留白；本行取值为 `0 auto`。 |
| 456 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 457 | <code>.page-heading {</code> | 选择匹配 `.page-heading` 的页面元素，开始为它们声明 CSS 样式。 |
| 458 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 459 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 460 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 461 | <code>  margin-bottom: 27px;</code> | 设置下方外边距；本行取值为 `27px`。 |
| 462 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 463 | <code>.page-heading p {</code> | 选择匹配 `.page-heading p` 的页面元素，开始为它们声明 CSS 样式。 |
| 464 | <code>  margin: 0;</code> | 设置元素外侧留白；本行取值为 `0`。 |
| 465 | <code>  color: #8a95a5;</code> | 设置文字颜色；本行取值为 `#8a95a5`。 |
| 466 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 467 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 468 | <code>.environment-tag {</code> | 选择匹配 `.environment-tag` 的页面元素，开始为它们声明 CSS 样式。 |
| 469 | <code>  padding: 6px 12px;</code> | 设置内容与边框之间的内边距；本行取值为 `6px 12px`。 |
| 470 | <code>  border: 1px solid #dce5f6;</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid #dce5f6`。 |
| 471 | <code>  border-radius: 5px;</code> | 设置圆角半径；本行取值为 `5px`。 |
| 472 | <code>  color: #7d91b8;</code> | 设置文字颜色；本行取值为 `#7d91b8`。 |
| 473 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 474 | <code>  background: #f1f5fc;</code> | 设置背景；本行取值为 `#f1f5fc`。 |
| 475 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 476 | <code>.workspace-footer {</code> | 选择匹配 `.workspace-footer` 的页面元素，开始为它们声明 CSS 样式。 |
| 477 | <code>  text-align: center;</code> | 设置行内内容水平对齐；本行取值为 `center`。 |
| 478 | <code>  padding: 15px 25px 25px;</code> | 设置内容与边框之间的内边距；本行取值为 `15px 25px 25px`。 |
| 479 | <code>  color: #a2adbc;</code> | 设置文字颜色；本行取值为 `#a2adbc`。 |
| 480 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 481 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 482 | <code>.mobile-menu {</code> | 选择匹配 `.mobile-menu` 的页面元素，开始为它们声明 CSS 样式。 |
| 483 | <code>  display: none;</code> | 设置布局方式；本行取值为 `none`。 |
| 484 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 485 | <code>.panel {</code> | 选择匹配 `.panel` 的页面元素，开始为它们声明 CSS 样式。 |
| 486 | <code>  border: 1px solid var(--border);</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid var(--border)`。 |
| 487 | <code>  border-radius: 10px;</code> | 设置圆角半径；本行取值为 `10px`。 |
| 488 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 489 | <code>  overflow: hidden;</code> | 设置内容溢出的处理方式；本行取值为 `hidden`。 |
| 490 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 491 | <code>.panel-header,</code> | 并列 CSS 选择器 `.panel-header`：与后续选择器共用同一组样式。 |
| 492 | <code>.toolbar {</code> | 选择匹配 `.toolbar` 的页面元素，开始为它们声明 CSS 样式。 |
| 493 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 494 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 495 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 496 | <code>  gap: 15px;</code> | 设置布局项目之间的间距；本行取值为 `15px`。 |
| 497 | <code>  padding: 23px 25px;</code> | 设置内容与边框之间的内边距；本行取值为 `23px 25px`。 |
| 498 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 499 | <code>.panel-header p,</code> | 并列 CSS 选择器 `.panel-header p`：与后续选择器共用同一组样式。 |
| 500 | <code>.toolbar p {</code> | 选择匹配 `.toolbar p` 的页面元素，开始为它们声明 CSS 样式。 |
| 501 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 502 | <code>  color: #9aa4b3;</code> | 设置文字颜色；本行取值为 `#9aa4b3`。 |
| 503 | <code>  margin: 5px 0 0;</code> | 设置元素外侧留白；本行取值为 `5px 0 0`。 |
| 504 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 505 | <code>.panel-header {</code> | 选择匹配 `.panel-header` 的页面元素，开始为它们声明 CSS 样式。 |
| 506 | <code>  border-bottom: 1px solid #eff2f7;</code> | 设置下边框；本行取值为 `1px solid #eff2f7`。 |
| 507 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 508 | <code>.count-pill {</code> | 选择匹配 `.count-pill` 的页面元素，开始为它们声明 CSS 样式。 |
| 509 | <code>  display: inline-block;</code> | 设置布局方式；本行取值为 `inline-block`。 |
| 510 | <code>  vertical-align: middle;</code> | 设置行内元素或表格单元格的垂直对齐；本行取值为 `middle`。 |
| 511 | <code>  margin-left: 6px;</code> | 设置左侧外边距；本行取值为 `6px`。 |
| 512 | <code>  padding: 2px 7px;</code> | 设置内容与边框之间的内边距；本行取值为 `2px 7px`。 |
| 513 | <code>  border-radius: 4px;</code> | 设置圆角半径；本行取值为 `4px`。 |
| 514 | <code>  background: #f0f3f8;</code> | 设置背景；本行取值为 `#f0f3f8`。 |
| 515 | <code>  color: #8390a5;</code> | 设置文字颜色；本行取值为 `#8390a5`。 |
| 516 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 517 | <code>  font-weight: 500;</code> | 设置字重；本行取值为 `500`。 |
| 518 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 519 | <code>.filter-bar {</code> | 选择匹配 `.filter-bar` 的页面元素，开始为它们声明 CSS 样式。 |
| 520 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 521 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 522 | <code>  gap: 12px;</code> | 设置布局项目之间的间距；本行取值为 `12px`。 |
| 523 | <code>  padding: 0 25px 23px;</code> | 设置内容与边框之间的内边距；本行取值为 `0 25px 23px`。 |
| 524 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 525 | <code>.filter-bar select {</code> | 选择匹配 `.filter-bar select` 的页面元素，开始为它们声明 CSS 样式。 |
| 526 | <code>  width: 160px;</code> | 设置宽度；本行取值为 `160px`。 |
| 527 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 528 | <code>.search-input {</code> | 选择匹配 `.search-input` 的页面元素，开始为它们声明 CSS 样式。 |
| 529 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 530 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 531 | <code>  gap: 7px;</code> | 设置布局项目之间的间距；本行取值为 `7px`。 |
| 532 | <code>  max-width: 320px;</code> | 限制最大宽度；本行取值为 `320px`。 |
| 533 | <code>  width: 100%;</code> | 设置宽度；本行取值为 `100%`。 |
| 534 | <code>  position: relative;</code> | 设置定位方式；本行取值为 `relative`。 |
| 535 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 536 | <code>.search-input &gt; span {</code> | 选择匹配 `.search-input > span` 的页面元素，开始为它们声明 CSS 样式。 |
| 537 | <code>  position: absolute;</code> | 设置定位方式；本行取值为 `absolute`。 |
| 538 | <code>  left: 12px;</code> | 设置左侧定位距离；本行取值为 `12px`。 |
| 539 | <code>  color: #a0aaba;</code> | 设置文字颜色；本行取值为 `#a0aaba`。 |
| 540 | <code>  font-size: 23px;</code> | 设置字号；本行取值为 `23px`。 |
| 541 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 542 | <code>.search-input input {</code> | 选择匹配 `.search-input input` 的页面元素，开始为它们声明 CSS 样式。 |
| 543 | <code>  padding-left: 36px;</code> | 设置左侧内边距；本行取值为 `36px`。 |
| 544 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 545 | <code>.table-wrap {</code> | 选择匹配 `.table-wrap` 的页面元素，开始为它们声明 CSS 样式。 |
| 546 | <code>  width: 100%;</code> | 设置宽度；本行取值为 `100%`。 |
| 547 | <code>  overflow-x: auto;</code> | 设置横向溢出的处理方式；本行取值为 `auto`。 |
| 548 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 549 | <code>table {</code> | 选择匹配 `table` 的页面元素，开始为它们声明 CSS 样式。 |
| 550 | <code>  width: 100%;</code> | 设置宽度；本行取值为 `100%`。 |
| 551 | <code>  border-collapse: collapse;</code> | 设置表格边框是否合并；本行取值为 `collapse`。 |
| 552 | <code>  text-align: left;</code> | 设置行内内容水平对齐；本行取值为 `left`。 |
| 553 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 554 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 555 | <code>th {</code> | 选择匹配 `th` 的页面元素，开始为它们声明 CSS 样式。 |
| 556 | <code>  padding: 14px 23px;</code> | 设置内容与边框之间的内边距；本行取值为 `14px 23px`。 |
| 557 | <code>  background: #f8fafd;</code> | 设置背景；本行取值为 `#f8fafd`。 |
| 558 | <code>  border-block: 1px solid #edf1f6;</code> | 设置文字块方向两侧边框；本行取值为 `1px solid #edf1f6`。 |
| 559 | <code>  color: #8b97a9;</code> | 设置文字颜色；本行取值为 `#8b97a9`。 |
| 560 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 561 | <code>  font-weight: 500;</code> | 设置字重；本行取值为 `500`。 |
| 562 | <code>  white-space: nowrap;</code> | 设置空白和换行处理方式；本行取值为 `nowrap`。 |
| 563 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 564 | <code>td {</code> | 选择匹配 `td` 的页面元素，开始为它们声明 CSS 样式。 |
| 565 | <code>  padding: 19px 23px;</code> | 设置内容与边框之间的内边距；本行取值为 `19px 23px`。 |
| 566 | <code>  border-bottom: 1px solid #f0f3f7;</code> | 设置下边框；本行取值为 `1px solid #f0f3f7`。 |
| 567 | <code>  line-height: 1.6;</code> | 设置行高；本行取值为 `1.6`。 |
| 568 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 569 | <code>tbody tr:last-child td {</code> | 选择匹配 `tbody tr:last-child td` 的页面元素，开始为它们声明 CSS 样式。 |
| 570 | <code>  border-bottom: 0;</code> | 设置下边框；本行取值为 `0`。 |
| 571 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 572 | <code>tbody tr:hover {</code> | 选择匹配 `tbody tr:hover` 的页面元素，开始为它们声明 CSS 样式。 |
| 573 | <code>  background: #fcfdff;</code> | 设置背景；本行取值为 `#fcfdff`。 |
| 574 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 575 | <code>.table-title {</code> | 选择匹配 `.table-title` 的页面元素，开始为它们声明 CSS 样式。 |
| 576 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 577 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 578 | <code>  color: #3a4a62;</code> | 设置文字颜色；本行取值为 `#3a4a62`。 |
| 579 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 580 | <code>.table-title.link-button:hover,</code> | 并列 CSS 选择器 `.table-title.link-button:hover`：与后续选择器共用同一组样式。 |
| 581 | <code>a.table-title:hover {</code> | 选择匹配 `a.table-title:hover` 的页面元素，开始为它们声明 CSS 样式。 |
| 582 | <code>  color: var(--primary);</code> | 设置文字颜色；本行取值为 `var(--primary)`。 |
| 583 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 584 | <code>.cell-note {</code> | 选择匹配 `.cell-note` 的页面元素，开始为它们声明 CSS 样式。 |
| 585 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 586 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 587 | <code>  font-weight: 400;</code> | 设置字重；本行取值为 `400`。 |
| 588 | <code>  color: #9aa6b6;</code> | 设置文字颜色；本行取值为 `#9aa6b6`。 |
| 589 | <code>  margin-top: 4px;</code> | 设置上方外边距；本行取值为 `4px`。 |
| 590 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 591 | <code>.product-cell {</code> | 选择匹配 `.product-cell` 的页面元素，开始为它们声明 CSS 样式。 |
| 592 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 593 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 594 | <code>  gap: 12px;</code> | 设置布局项目之间的间距；本行取值为 `12px`。 |
| 595 | <code>  min-width: 175px;</code> | 限制最小宽度；本行取值为 `175px`。 |
| 596 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 597 | <code>.product-cell strong {</code> | 选择匹配 `.product-cell strong` 的页面元素，开始为它们声明 CSS 样式。 |
| 598 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 599 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 600 | <code>.product-thumbnail {</code> | 选择匹配 `.product-thumbnail` 的页面元素，开始为它们声明 CSS 样式。 |
| 601 | <code>  display: inline-grid;</code> | 设置布局方式；本行取值为 `inline-grid`。 |
| 602 | <code>  place-items: center;</code> | 同时设置网格项目的水平与垂直对齐；本行取值为 `center`。 |
| 603 | <code>  width: 38px;</code> | 设置宽度；本行取值为 `38px`。 |
| 604 | <code>  height: 38px;</code> | 设置高度；本行取值为 `38px`。 |
| 605 | <code>  flex-shrink: 0;</code> | 设置空间不足时的收缩比例；本行取值为 `0`。 |
| 606 | <code>  border: 1px solid #e6ecf6;</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid #e6ecf6`。 |
| 607 | <code>  border-radius: 8px;</code> | 设置圆角半径；本行取值为 `8px`。 |
| 608 | <code>  background: linear-gradient(145deg, #f5f8ff, #edf2fb);</code> | 设置背景；本行取值为 `linear-gradient(145deg, #f5f8ff, #edf2fb)`。 |
| 609 | <code>  color: #7c95c1;</code> | 设置文字颜色；本行取值为 `#7c95c1`。 |
| 610 | <code>  font-size: 17px;</code> | 设置字号；本行取值为 `17px`。 |
| 611 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 612 | <code>.money-cell {</code> | 选择匹配 `.money-cell` 的页面元素，开始为它们声明 CSS 样式。 |
| 613 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 614 | <code>  white-space: nowrap;</code> | 设置空白和换行处理方式；本行取值为 `nowrap`。 |
| 615 | <code>  font-variant-numeric: tabular-nums;</code> | 为数字启用等宽等排版特性，方便金额/数量对齐；本行取值为 `tabular-nums`。 |
| 616 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 617 | <code>.low-stock {</code> | 选择匹配 `.low-stock` 的页面元素，开始为它们声明 CSS 样式。 |
| 618 | <code>  color: #da9650;</code> | 设置文字颜色；本行取值为 `#da9650`。 |
| 619 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 620 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 621 | <code>.badge {</code> | 选择匹配 `.badge` 的页面元素，开始为它们声明 CSS 样式。 |
| 622 | <code>  display: inline-block;</code> | 设置布局方式；本行取值为 `inline-block`。 |
| 623 | <code>  padding: 3px 9px;</code> | 设置内容与边框之间的内边距；本行取值为 `3px 9px`。 |
| 624 | <code>  border-radius: 4px;</code> | 设置圆角半径；本行取值为 `4px`。 |
| 625 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 626 | <code>  line-height: 1.5;</code> | 设置行高；本行取值为 `1.5`。 |
| 627 | <code>  white-space: nowrap;</code> | 设置空白和换行处理方式；本行取值为 `nowrap`。 |
| 628 | <code>  background: #f0f3f8;</code> | 设置背景；本行取值为 `#f0f3f8`。 |
| 629 | <code>  color: #8893a5;</code> | 设置文字颜色；本行取值为 `#8893a5`。 |
| 630 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 631 | <code>.badge.on-sale,</code> | 并列 CSS 选择器 `.badge.on-sale`：与后续选择器共用同一组样式。 |
| 632 | <code>.badge.completed {</code> | 选择匹配 `.badge.completed` 的页面元素，开始为它们声明 CSS 样式。 |
| 633 | <code>  color: #32967d;</code> | 设置文字颜色；本行取值为 `#32967d`。 |
| 634 | <code>  background: #eaf7f2;</code> | 设置背景；本行取值为 `#eaf7f2`。 |
| 635 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 636 | <code>.badge.pending {</code> | 选择匹配 `.badge.pending` 的页面元素，开始为它们声明 CSS 样式。 |
| 637 | <code>  color: #b68b3c;</code> | 设置文字颜色；本行取值为 `#b68b3c`。 |
| 638 | <code>  background: #fdf6e8;</code> | 设置背景；本行取值为 `#fdf6e8`。 |
| 639 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 640 | <code>.badge.paid,</code> | 并列 CSS 选择器 `.badge.paid`：与后续选择器共用同一组样式。 |
| 641 | <code>.badge.admin {</code> | 选择匹配 `.badge.admin` 的页面元素，开始为它们声明 CSS 样式。 |
| 642 | <code>  color: #517ac6;</code> | 设置文字颜色；本行取值为 `#517ac6`。 |
| 643 | <code>  background: #edf3ff;</code> | 设置背景；本行取值为 `#edf3ff`。 |
| 644 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 645 | <code>.badge.shipped {</code> | 选择匹配 `.badge.shipped` 的页面元素，开始为它们声明 CSS 样式。 |
| 646 | <code>  color: #8b68c4;</code> | 设置文字颜色；本行取值为 `#8b68c4`。 |
| 647 | <code>  background: #f2edfa;</code> | 设置背景；本行取值为 `#f2edfa`。 |
| 648 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 649 | <code>.badge.cancelled,</code> | 并列 CSS 选择器 `.badge.cancelled`：与后续选择器共用同一组样式。 |
| 650 | <code>.badge.off-sale,</code> | 并列 CSS 选择器 `.badge.off-sale`：与后续选择器共用同一组样式。 |
| 651 | <code>.badge.staff {</code> | 选择匹配 `.badge.staff` 的页面元素，开始为它们声明 CSS 样式。 |
| 652 | <code>  color: #8d99aa;</code> | 设置文字颜色；本行取值为 `#8d99aa`。 |
| 653 | <code>  background: #f0f3f8;</code> | 设置背景；本行取值为 `#f0f3f8`。 |
| 654 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 655 | <code>.row-actions {</code> | 选择匹配 `.row-actions` 的页面元素，开始为它们声明 CSS 样式。 |
| 656 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 657 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 658 | <code>  gap: 15px;</code> | 设置布局项目之间的间距；本行取值为 `15px`。 |
| 659 | <code>  white-space: nowrap;</code> | 设置空白和换行处理方式；本行取值为 `nowrap`。 |
| 660 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 661 | <code>.row-actions button {</code> | 选择匹配 `.row-actions button` 的页面元素，开始为它们声明 CSS 样式。 |
| 662 | <code>  border: 0;</code> | 设置边框宽度、样式和颜色；本行取值为 `0`。 |
| 663 | <code>  padding: 3px 0;</code> | 设置内容与边框之间的内边距；本行取值为 `3px 0`。 |
| 664 | <code>  color: #5e85d6;</code> | 设置文字颜色；本行取值为 `#5e85d6`。 |
| 665 | <code>  background: transparent;</code> | 设置背景；本行取值为 `transparent`。 |
| 666 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 667 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 668 | <code>.row-actions button:hover {</code> | 选择匹配 `.row-actions button:hover` 的页面元素，开始为它们声明 CSS 样式。 |
| 669 | <code>  text-decoration: underline;</code> | 设置文字装饰；本行取值为 `underline`。 |
| 670 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 671 | <code>.actions-column {</code> | 选择匹配 `.actions-column` 的页面元素，开始为它们声明 CSS 样式。 |
| 672 | <code>  width: 160px;</code> | 设置宽度；本行取值为 `160px`。 |
| 673 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 674 | <code>.id-column {</code> | 选择匹配 `.id-column` 的页面元素，开始为它们声明 CSS 样式。 |
| 675 | <code>  width: 100px;</code> | 设置宽度；本行取值为 `100px`。 |
| 676 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 677 | <code>.description-cell {</code> | 选择匹配 `.description-cell` 的页面元素，开始为它们声明 CSS 样式。 |
| 678 | <code>  max-width: 440px;</code> | 限制最大宽度；本行取值为 `440px`。 |
| 679 | <code>  color: #8a96a7;</code> | 设置文字颜色；本行取值为 `#8a96a7`。 |
| 680 | <code>  overflow-wrap: anywhere;</code> | 允许过长内容在必要时换行；本行取值为 `anywhere`。 |
| 681 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 682 | <code>.empty-cell {</code> | 选择匹配 `.empty-cell` 的页面元素，开始为它们声明 CSS 样式。 |
| 683 | <code>  padding: 45px 20px;</code> | 设置内容与边框之间的内边距；本行取值为 `45px 20px`。 |
| 684 | <code>  text-align: center;</code> | 设置行内内容水平对齐；本行取值为 `center`。 |
| 685 | <code>  color: #a1acbc;</code> | 设置文字颜色；本行取值为 `#a1acbc`。 |
| 686 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 687 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 688 | <code>.loading-state {</code> | 选择匹配 `.loading-state` 的页面元素，开始为它们声明 CSS 样式。 |
| 689 | <code>  padding: 60px 20px;</code> | 设置内容与边框之间的内边距；本行取值为 `60px 20px`。 |
| 690 | <code>  text-align: center;</code> | 设置行内内容水平对齐；本行取值为 `center`。 |
| 691 | <code>  color: #94a0b4;</code> | 设置文字颜色；本行取值为 `#94a0b4`。 |
| 692 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 693 | <code>.pagination {</code> | 选择匹配 `.pagination` 的页面元素，开始为它们声明 CSS 样式。 |
| 694 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 695 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 696 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 697 | <code>  padding: 20px 25px;</code> | 设置内容与边框之间的内边距；本行取值为 `20px 25px`。 |
| 698 | <code>  border-top: 1px solid #eff2f7;</code> | 设置上边框；本行取值为 `1px solid #eff2f7`。 |
| 699 | <code>  color: #99a4b4;</code> | 设置文字颜色；本行取值为 `#99a4b4`。 |
| 700 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 701 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 702 | <code>.pagination &gt; div {</code> | 选择匹配 `.pagination > div` 的页面元素，开始为它们声明 CSS 样式。 |
| 703 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 704 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 705 | <code>  gap: 14px;</code> | 设置布局项目之间的间距；本行取值为 `14px`。 |
| 706 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 707 | <code>.error-banner {</code> | 选择匹配 `.error-banner` 的页面元素，开始为它们声明 CSS 样式。 |
| 708 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 709 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 710 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 711 | <code>  gap: 15px;</code> | 设置布局项目之间的间距；本行取值为 `15px`。 |
| 712 | <code>  margin: 0 25px 18px;</code> | 设置元素外侧留白；本行取值为 `0 25px 18px`。 |
| 713 | <code>  padding: 12px 15px;</code> | 设置内容与边框之间的内边距；本行取值为 `12px 15px`。 |
| 714 | <code>  border-radius: 6px;</code> | 设置圆角半径；本行取值为 `6px`。 |
| 715 | <code>  background: #fff0f0;</code> | 设置背景；本行取值为 `#fff0f0`。 |
| 716 | <code>  color: #cf5860;</code> | 设置文字颜色；本行取值为 `#cf5860`。 |
| 717 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 718 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 719 | <code>.error-banner button {</code> | 选择匹配 `.error-banner button` 的页面元素，开始为它们声明 CSS 样式。 |
| 720 | <code>  background: transparent;</code> | 设置背景；本行取值为 `transparent`。 |
| 721 | <code>  border: 0;</code> | 设置边框宽度、样式和颜色；本行取值为 `0`。 |
| 722 | <code>  color: #c84a54;</code> | 设置文字颜色；本行取值为 `#c84a54`。 |
| 723 | <code>  text-decoration: underline;</code> | 设置文字装饰；本行取值为 `underline`。 |
| 724 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 725 | <code>.stats-grid {</code> | 选择匹配 `.stats-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 726 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 727 | <code>  grid-template-columns: repeat(4, minmax(0, 1fr));</code> | 定义网格列数与各列宽度；本行取值为 `repeat(4, minmax(0, 1fr))`。 |
| 728 | <code>  gap: 20px;</code> | 设置布局项目之间的间距；本行取值为 `20px`。 |
| 729 | <code>  margin-bottom: 24px;</code> | 设置下方外边距；本行取值为 `24px`。 |
| 730 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 731 | <code>.stat-card {</code> | 选择匹配 `.stat-card` 的页面元素，开始为它们声明 CSS 样式。 |
| 732 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 733 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 734 | <code>  align-items: flex-start;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `flex-start`。 |
| 735 | <code>  padding: 24px 22px;</code> | 设置内容与边框之间的内边距；本行取值为 `24px 22px`。 |
| 736 | <code>  border: 1px solid var(--border);</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid var(--border)`。 |
| 737 | <code>  border-radius: 10px;</code> | 设置圆角半径；本行取值为 `10px`。 |
| 738 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 739 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 740 | <code>.stat-card p {</code> | 选择匹配 `.stat-card p` 的页面元素，开始为它们声明 CSS 样式。 |
| 741 | <code>  margin-bottom: 13px;</code> | 设置下方外边距；本行取值为 `13px`。 |
| 742 | <code>  color: #8b97a9;</code> | 设置文字颜色；本行取值为 `#8b97a9`。 |
| 743 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 744 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 745 | <code>.stat-card strong {</code> | 选择匹配 `.stat-card strong` 的页面元素，开始为它们声明 CSS 样式。 |
| 746 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 747 | <code>  font-family: Arial, &quot;Microsoft YaHei&quot;, sans-serif;</code> | 指定字体及备用字体；本行取值为 `Arial, "Microsoft YaHei", sans-serif`。 |
| 748 | <code>  font-size: 27px;</code> | 设置字号；本行取值为 `27px`。 |
| 749 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 750 | <code>  letter-spacing: -0.7px;</code> | 设置字符间距；本行取值为 `-0.7px`。 |
| 751 | <code>  font-variant-numeric: tabular-nums;</code> | 为数字启用等宽等排版特性，方便金额/数量对齐；本行取值为 `tabular-nums`。 |
| 752 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 753 | <code>.stat-card small {</code> | 选择匹配 `.stat-card small` 的页面元素，开始为它们声明 CSS 样式。 |
| 754 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 755 | <code>  margin-top: 13px;</code> | 设置上方外边距；本行取值为 `13px`。 |
| 756 | <code>  color: #a5aebc;</code> | 设置文字颜色；本行取值为 `#a5aebc`。 |
| 757 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 758 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 759 | <code>.stat-symbol {</code> | 选择匹配 `.stat-symbol` 的页面元素，开始为它们声明 CSS 样式。 |
| 760 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 761 | <code>  place-items: center;</code> | 同时设置网格项目的水平与垂直对齐；本行取值为 `center`。 |
| 762 | <code>  width: 38px;</code> | 设置宽度；本行取值为 `38px`。 |
| 763 | <code>  height: 38px;</code> | 设置高度；本行取值为 `38px`。 |
| 764 | <code>  flex-shrink: 0;</code> | 设置空间不足时的收缩比例；本行取值为 `0`。 |
| 765 | <code>  margin-left: 8px;</code> | 设置左侧外边距；本行取值为 `8px`。 |
| 766 | <code>  border-radius: 9px;</code> | 设置圆角半径；本行取值为 `9px`。 |
| 767 | <code>  font-size: 21px;</code> | 设置字号；本行取值为 `21px`。 |
| 768 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 769 | <code>.stat-symbol.blue {</code> | 选择匹配 `.stat-symbol.blue` 的页面元素，开始为它们声明 CSS 样式。 |
| 770 | <code>  background: #eef3ff;</code> | 设置背景；本行取值为 `#eef3ff`。 |
| 771 | <code>  color: #6487d9;</code> | 设置文字颜色；本行取值为 `#6487d9`。 |
| 772 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 773 | <code>.stat-symbol.purple {</code> | 选择匹配 `.stat-symbol.purple` 的页面元素，开始为它们声明 CSS 样式。 |
| 774 | <code>  background: #f1edfc;</code> | 设置背景；本行取值为 `#f1edfc`。 |
| 775 | <code>  color: #9a7bd3;</code> | 设置文字颜色；本行取值为 `#9a7bd3`。 |
| 776 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 777 | <code>.stat-symbol.green {</code> | 选择匹配 `.stat-symbol.green` 的页面元素，开始为它们声明 CSS 样式。 |
| 778 | <code>  background: #eaf7f1;</code> | 设置背景；本行取值为 `#eaf7f1`。 |
| 779 | <code>  color: #5eae8d;</code> | 设置文字颜色；本行取值为 `#5eae8d`。 |
| 780 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 781 | <code>.stat-symbol.orange {</code> | 选择匹配 `.stat-symbol.orange` 的页面元素，开始为它们声明 CSS 样式。 |
| 782 | <code>  background: #fff3e8;</code> | 设置背景；本行取值为 `#fff3e8`。 |
| 783 | <code>  color: #d5a06c;</code> | 设置文字颜色；本行取值为 `#d5a06c`。 |
| 784 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 785 | <code>.welcome-banner {</code> | 选择匹配 `.welcome-banner` 的页面元素，开始为它们声明 CSS 样式。 |
| 786 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 787 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 788 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 789 | <code>  gap: 24px;</code> | 设置布局项目之间的间距；本行取值为 `24px`。 |
| 790 | <code>  padding: 25px 30px;</code> | 设置内容与边框之间的内边距；本行取值为 `25px 30px`。 |
| 791 | <code>  background: linear-gradient(105deg, #e9f0ff, #f1f5ff);</code> | 设置背景；本行取值为 `linear-gradient(105deg, #e9f0ff, #f1f5ff)`。 |
| 792 | <code>  border: 1px solid #e0e9fc;</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid #e0e9fc`。 |
| 793 | <code>  border-radius: 10px;</code> | 设置圆角半径；本行取值为 `10px`。 |
| 794 | <code>  margin-bottom: 24px;</code> | 设置下方外边距；本行取值为 `24px`。 |
| 795 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 796 | <code>.eyebrow {</code> | 选择匹配 `.eyebrow` 的页面元素，开始为它们声明 CSS 样式。 |
| 797 | <code>  color: #86a0cf;</code> | 设置文字颜色；本行取值为 `#86a0cf`。 |
| 798 | <code>  font-size: 9px;</code> | 设置字号；本行取值为 `9px`。 |
| 799 | <code>  letter-spacing: 2px;</code> | 设置字符间距；本行取值为 `2px`。 |
| 800 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 801 | <code>  margin-bottom: 10px;</code> | 设置下方外边距；本行取值为 `10px`。 |
| 802 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 803 | <code>.welcome-banner h2 {</code> | 选择匹配 `.welcome-banner h2` 的页面元素，开始为它们声明 CSS 样式。 |
| 804 | <code>  color: #4a6592;</code> | 设置文字颜色；本行取值为 `#4a6592`。 |
| 805 | <code>  font-size: 17px;</code> | 设置字号；本行取值为 `17px`。 |
| 806 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 807 | <code>.welcome-banner p:last-child {</code> | 选择匹配 `.welcome-banner p:last-child` 的页面元素，开始为它们声明 CSS 样式。 |
| 808 | <code>  color: #94a5c3;</code> | 设置文字颜色；本行取值为 `#94a5c3`。 |
| 809 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 810 | <code>  margin-bottom: 0;</code> | 设置下方外边距；本行取值为 `0`。 |
| 811 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 812 | <code>.welcome-banner .button {</code> | 选择匹配 `.welcome-banner .button` 的页面元素，开始为它们声明 CSS 样式。 |
| 813 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 814 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 815 | <code>.dashboard-grid {</code> | 选择匹配 `.dashboard-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 816 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 817 | <code>  grid-template-columns: 1.5fr 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1.5fr 1fr`。 |
| 818 | <code>  gap: 24px;</code> | 设置布局项目之间的间距；本行取值为 `24px`。 |
| 819 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 820 | <code>.stock-list {</code> | 选择匹配 `.stock-list` 的页面元素，开始为它们声明 CSS 样式。 |
| 821 | <code>  padding: 0 25px;</code> | 设置内容与边框之间的内边距；本行取值为 `0 25px`。 |
| 822 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 823 | <code>.stock-item {</code> | 选择匹配 `.stock-item` 的页面元素，开始为它们声明 CSS 样式。 |
| 824 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 825 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 826 | <code>  gap: 12px;</code> | 设置布局项目之间的间距；本行取值为 `12px`。 |
| 827 | <code>  padding: 20px 0;</code> | 设置内容与边框之间的内边距；本行取值为 `20px 0`。 |
| 828 | <code>  border-bottom: 1px solid #f0f3f7;</code> | 设置下边框；本行取值为 `1px solid #f0f3f7`。 |
| 829 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 830 | <code>.stock-item:last-child {</code> | 选择匹配 `.stock-item:last-child` 的页面元素，开始为它们声明 CSS 样式。 |
| 831 | <code>  border-bottom: 0;</code> | 设置下边框；本行取值为 `0`。 |
| 832 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 833 | <code>.stock-item strong {</code> | 选择匹配 `.stock-item strong` 的页面元素，开始为它们声明 CSS 样式。 |
| 834 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 835 | <code>  font-weight: 500;</code> | 设置字重；本行取值为 `500`。 |
| 836 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 837 | <code>.stock-item small {</code> | 选择匹配 `.stock-item small` 的页面元素，开始为它们声明 CSS 样式。 |
| 838 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 839 | <code>  margin-top: 5px;</code> | 设置上方外边距；本行取值为 `5px`。 |
| 840 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 841 | <code>  color: #9aa6b6;</code> | 设置文字颜色；本行取值为 `#9aa6b6`。 |
| 842 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 843 | <code>.stock-count {</code> | 选择匹配 `.stock-count` 的页面元素，开始为它们声明 CSS 样式。 |
| 844 | <code>  margin-left: auto;</code> | 设置左侧外边距；本行取值为 `auto`。 |
| 845 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 846 | <code>  color: #cf9d67;</code> | 设置文字颜色；本行取值为 `#cf9d67`。 |
| 847 | <code>  white-space: nowrap;</code> | 设置空白和换行处理方式；本行取值为 `nowrap`。 |
| 848 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 849 | <code>.modal-backdrop {</code> | 选择匹配 `.modal-backdrop` 的页面元素，开始为它们声明 CSS 样式。 |
| 850 | <code>  position: fixed;</code> | 设置定位方式；本行取值为 `fixed`。 |
| 851 | <code>  inset: 0;</code> | 同时设置四边定位距离；本行取值为 `0`。 |
| 852 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 853 | <code>  place-items: center;</code> | 同时设置网格项目的水平与垂直对齐；本行取值为 `center`。 |
| 854 | <code>  padding: 24px;</code> | 设置内容与边框之间的内边距；本行取值为 `24px`。 |
| 855 | <code>  background: #19263e70;</code> | 设置背景；本行取值为 `#19263e70`。 |
| 856 | <code>  backdrop-filter: blur(2px);</code> | 对元素背后的内容应用模糊等滤镜；本行取值为 `blur(2px)`。 |
| 857 | <code>  z-index: 100;</code> | 设置定位元素的层叠顺序；本行取值为 `100`。 |
| 858 | <code>  overflow-y: auto;</code> | 设置纵向溢出的处理方式；本行取值为 `auto`。 |
| 859 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 860 | <code>.modal-backdrop.elevated {</code> | 选择匹配 `.modal-backdrop.elevated` 的页面元素，开始为它们声明 CSS 样式。 |
| 861 | <code>  z-index: 150;</code> | 设置定位元素的层叠顺序；本行取值为 `150`。 |
| 862 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 863 | <code>.modal {</code> | 选择匹配 `.modal` 的页面元素，开始为它们声明 CSS 样式。 |
| 864 | <code>  width: 100%;</code> | 设置宽度；本行取值为 `100%`。 |
| 865 | <code>  max-width: 480px;</code> | 限制最大宽度；本行取值为 `480px`。 |
| 866 | <code>  max-height: calc(100dvh - 48px);</code> | 限制最大高度；本行取值为 `calc(100dvh - 48px)`。 |
| 867 | <code>  overflow-y: auto;</code> | 设置纵向溢出的处理方式；本行取值为 `auto`。 |
| 868 | <code>  border-radius: 12px;</code> | 设置圆角半径；本行取值为 `12px`。 |
| 869 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 870 | <code>  box-shadow: 0 22px 80px #19283e30;</code> | 为元素添加阴影；本行取值为 `0 22px 80px #19283e30`。 |
| 871 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 872 | <code>.modal.wide {</code> | 选择匹配 `.modal.wide` 的页面元素，开始为它们声明 CSS 样式。 |
| 873 | <code>  max-width: 760px;</code> | 限制最大宽度；本行取值为 `760px`。 |
| 874 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 875 | <code>.modal-header {</code> | 选择匹配 `.modal-header` 的页面元素，开始为它们声明 CSS 样式。 |
| 876 | <code>  position: sticky;</code> | 设置定位方式；本行取值为 `sticky`。 |
| 877 | <code>  top: 0;</code> | 设置顶部定位距离；本行取值为 `0`。 |
| 878 | <code>  z-index: 1;</code> | 设置定位元素的层叠顺序；本行取值为 `1`。 |
| 879 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 880 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 881 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 882 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 883 | <code>  padding: 19px 25px;</code> | 设置内容与边框之间的内边距；本行取值为 `19px 25px`。 |
| 884 | <code>  border-bottom: 1px solid var(--border);</code> | 设置下边框；本行取值为 `1px solid var(--border)`。 |
| 885 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 886 | <code>.modal-header h2 {</code> | 选择匹配 `.modal-header h2` 的页面元素，开始为它们声明 CSS 样式。 |
| 887 | <code>  margin: 0;</code> | 设置元素外侧留白；本行取值为 `0`。 |
| 888 | <code>  font-size: 17px;</code> | 设置字号；本行取值为 `17px`。 |
| 889 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 890 | <code>.modal-body {</code> | 选择匹配 `.modal-body` 的页面元素，开始为它们声明 CSS 样式。 |
| 891 | <code>  padding: 25px;</code> | 设置内容与边框之间的内边距；本行取值为 `25px`。 |
| 892 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 893 | <code>.field {</code> | 选择匹配 `.field` 的页面元素，开始为它们声明 CSS 样式。 |
| 894 | <code>  margin-bottom: 19px;</code> | 设置下方外边距；本行取值为 `19px`。 |
| 895 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 896 | <code>.field label {</code> | 选择匹配 `.field label` 的页面元素，开始为它们声明 CSS 样式。 |
| 897 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 898 | <code>  margin-bottom: 8px;</code> | 设置下方外边距；本行取值为 `8px`。 |
| 899 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 900 | <code>  color: #66758d;</code> | 设置文字颜色；本行取值为 `#66758d`。 |
| 901 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 902 | <code>.field label span {</code> | 选择匹配 `.field label span` 的页面元素，开始为它们声明 CSS 样式。 |
| 903 | <code>  color: #d16b74;</code> | 设置文字颜色；本行取值为 `#d16b74`。 |
| 904 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 905 | <code>.field small,</code> | 并列 CSS 选择器 `.field small`：与后续选择器共用同一组样式。 |
| 906 | <code>.field-hint {</code> | 选择匹配 `.field-hint` 的页面元素，开始为它们声明 CSS 样式。 |
| 907 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 908 | <code>  margin: 8px 0 0;</code> | 设置元素外侧留白；本行取值为 `8px 0 0`。 |
| 909 | <code>  color: #9aa5b5;</code> | 设置文字颜色；本行取值为 `#9aa5b5`。 |
| 910 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 911 | <code>  line-height: 1.7;</code> | 设置行高；本行取值为 `1.7`。 |
| 912 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 913 | <code>.form-grid {</code> | 选择匹配 `.form-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 914 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 915 | <code>  grid-template-columns: 1fr 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1fr 1fr`。 |
| 916 | <code>  gap: 0 20px;</code> | 设置布局项目之间的间距；本行取值为 `0 20px`。 |
| 917 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 918 | <code>.full-width {</code> | 选择匹配 `.full-width` 的页面元素，开始为它们声明 CSS 样式。 |
| 919 | <code>  grid-column: 1 / -1;</code> | 指定项目占据的网格列；本行取值为 `1 / -1`。 |
| 920 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 921 | <code>.form-actions {</code> | 选择匹配 `.form-actions` 的页面元素，开始为它们声明 CSS 样式。 |
| 922 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 923 | <code>  justify-content: flex-end;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `flex-end`。 |
| 924 | <code>  flex-wrap: wrap;</code> | 设置弹性项目是否换行；本行取值为 `wrap`。 |
| 925 | <code>  gap: 10px;</code> | 设置布局项目之间的间距；本行取值为 `10px`。 |
| 926 | <code>  padding-top: 21px;</code> | 设置顶部内边距；本行取值为 `21px`。 |
| 927 | <code>  margin-top: 10px;</code> | 设置上方外边距；本行取值为 `10px`。 |
| 928 | <code>  border-top: 1px solid var(--border);</code> | 设置上边框；本行取值为 `1px solid var(--border)`。 |
| 929 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 930 | <code>.form-section-title {</code> | 选择匹配 `.form-section-title` 的页面元素，开始为它们声明 CSS 样式。 |
| 931 | <code>  margin-bottom: 18px;</code> | 设置下方外边距；本行取值为 `18px`。 |
| 932 | <code>  font-size: 13px;</code> | 设置字号；本行取值为 `13px`。 |
| 933 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 934 | <code>.inline-error {</code> | 选择匹配 `.inline-error` 的页面元素，开始为它们声明 CSS 样式。 |
| 935 | <code>  padding: 10px 12px;</code> | 设置内容与边框之间的内边距；本行取值为 `10px 12px`。 |
| 936 | <code>  background: #fff1f1;</code> | 设置背景；本行取值为 `#fff1f1`。 |
| 937 | <code>  border-radius: 5px;</code> | 设置圆角半径；本行取值为 `5px`。 |
| 938 | <code>  color: #d45e66;</code> | 设置文字颜色；本行取值为 `#d45e66`。 |
| 939 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 940 | <code>  line-height: 1.6;</code> | 设置行高；本行取值为 `1.6`。 |
| 941 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 942 | <code>.confirm-message {</code> | 选择匹配 `.confirm-message` 的页面元素，开始为它们声明 CSS 样式。 |
| 943 | <code>  white-space: pre-wrap;</code> | 设置空白和换行处理方式；本行取值为 `pre-wrap`。 |
| 944 | <code>  color: #7a879b;</code> | 设置文字颜色；本行取值为 `#7a879b`。 |
| 945 | <code>  font-size: 13px;</code> | 设置字号；本行取值为 `13px`。 |
| 946 | <code>  line-height: 1.9;</code> | 设置行高；本行取值为 `1.9`。 |
| 947 | <code>  margin-bottom: 25px;</code> | 设置下方外边距；本行取值为 `25px`。 |
| 948 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 949 | <code>.toast-list {</code> | 选择匹配 `.toast-list` 的页面元素，开始为它们声明 CSS 样式。 |
| 950 | <code>  position: fixed;</code> | 设置定位方式；本行取值为 `fixed`。 |
| 951 | <code>  top: 20px;</code> | 设置顶部定位距离；本行取值为 `20px`。 |
| 952 | <code>  right: 25px;</code> | 设置右侧定位距离；本行取值为 `25px`。 |
| 953 | <code>  z-index: 200;</code> | 设置定位元素的层叠顺序；本行取值为 `200`。 |
| 954 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 955 | <code>  flex-direction: column;</code> | 设置弹性布局排列方向；本行取值为 `column`。 |
| 956 | <code>  align-items: flex-end;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `flex-end`。 |
| 957 | <code>  gap: 10px;</code> | 设置布局项目之间的间距；本行取值为 `10px`。 |
| 958 | <code>  width: min(440px, calc(100% - 40px));</code> | 设置宽度；本行取值为 `min(440px, calc(100% - 40px))`。 |
| 959 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 960 | <code>.toast {</code> | 选择匹配 `.toast` 的页面元素，开始为它们声明 CSS 样式。 |
| 961 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 962 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 963 | <code>  gap: 11px;</code> | 设置布局项目之间的间距；本行取值为 `11px`。 |
| 964 | <code>  padding: 13px 16px;</code> | 设置内容与边框之间的内边距；本行取值为 `13px 16px`。 |
| 965 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 966 | <code>  border: 1px solid #d9ece4;</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid #d9ece4`。 |
| 967 | <code>  border-radius: 8px;</code> | 设置圆角半径；本行取值为 `8px`。 |
| 968 | <code>  box-shadow: 0 8px 30px #24314813;</code> | 为元素添加阴影；本行取值为 `0 8px 30px #24314813`。 |
| 969 | <code>  color: #419d7d;</code> | 设置文字颜色；本行取值为 `#419d7d`。 |
| 970 | <code>  max-width: 100%;</code> | 限制最大宽度；本行取值为 `100%`。 |
| 971 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 972 | <code>.toast.error {</code> | 选择匹配 `.toast.error` 的页面元素，开始为它们声明 CSS 样式。 |
| 973 | <code>  border-color: #f0d7d9;</code> | 设置边框颜色；本行取值为 `#f0d7d9`。 |
| 974 | <code>  color: #cd626c;</code> | 设置文字颜色；本行取值为 `#cd626c`。 |
| 975 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 976 | <code>.toast p {</code> | 选择匹配 `.toast p` 的页面元素，开始为它们声明 CSS 样式。 |
| 977 | <code>  margin: 0;</code> | 设置元素外侧留白；本行取值为 `0`。 |
| 978 | <code>  color: #65748a;</code> | 设置文字颜色；本行取值为 `#65748a`。 |
| 979 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 980 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 981 | <code>.toast button {</code> | 选择匹配 `.toast button` 的页面元素，开始为它们声明 CSS 样式。 |
| 982 | <code>  border: 0;</code> | 设置边框宽度、样式和颜色；本行取值为 `0`。 |
| 983 | <code>  background: transparent;</code> | 设置背景；本行取值为 `transparent`。 |
| 984 | <code>  color: #a7b0bf;</code> | 设置文字颜色；本行取值为 `#a7b0bf`。 |
| 985 | <code>  margin-left: 5px;</code> | 设置左侧外边距；本行取值为 `5px`。 |
| 986 | <code>  font-size: 18px;</code> | 设置字号；本行取值为 `18px`。 |
| 987 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 988 | <code>.order-product-search {</code> | 选择匹配 `.order-product-search` 的页面元素，开始为它们声明 CSS 样式。 |
| 989 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 990 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 991 | <code>  gap: 10px;</code> | 设置布局项目之间的间距；本行取值为 `10px`。 |
| 992 | <code>  margin-bottom: 10px;</code> | 设置下方外边距；本行取值为 `10px`。 |
| 993 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 994 | <code>.order-product-search input,</code> | 并列 CSS 选择器 `.order-product-search input`：与后续选择器共用同一组样式。 |
| 995 | <code>.order-product-search select {</code> | 选择匹配 `.order-product-search select` 的页面元素，开始为它们声明 CSS 样式。 |
| 996 | <code>  min-width: 0;</code> | 限制最小宽度；本行取值为 `0`。 |
| 997 | <code>  flex: 1;</code> | 设置弹性项目的伸缩与基础尺寸；本行取值为 `1`。 |
| 998 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 999 | <code>.order-items {</code> | 选择匹配 `.order-items` 的页面元素，开始为它们声明 CSS 样式。 |
| 1000 | <code>  margin-top: 20px;</code> | 设置上方外边距；本行取值为 `20px`。 |
| 1001 | <code>  border: 1px solid var(--border);</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid var(--border)`。 |
| 1002 | <code>  border-radius: 6px;</code> | 设置圆角半径；本行取值为 `6px`。 |
| 1003 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1004 | <code>.order-items th {</code> | 选择匹配 `.order-items th` 的页面元素，开始为它们声明 CSS 样式。 |
| 1005 | <code>  border-top: 0;</code> | 设置上边框；本行取值为 `0`。 |
| 1006 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1007 | <code>.order-items th,</code> | 并列 CSS 选择器 `.order-items th`：与后续选择器共用同一组样式。 |
| 1008 | <code>.order-items td {</code> | 选择匹配 `.order-items td` 的页面元素，开始为它们声明 CSS 样式。 |
| 1009 | <code>  padding: 11px 14px;</code> | 设置内容与边框之间的内边距；本行取值为 `11px 14px`。 |
| 1010 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1011 | <code>.quantity-input {</code> | 选择匹配 `.quantity-input` 的页面元素，开始为它们声明 CSS 样式。 |
| 1012 | <code>  width: 77px;</code> | 设置宽度；本行取值为 `77px`。 |
| 1013 | <code>  padding: 6px 8px;</code> | 设置内容与边框之间的内边距；本行取值为 `6px 8px`。 |
| 1014 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1015 | <code>.order-total {</code> | 选择匹配 `.order-total` 的页面元素，开始为它们声明 CSS 样式。 |
| 1016 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 1017 | <code>  justify-content: flex-end;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `flex-end`。 |
| 1018 | <code>  gap: 17px;</code> | 设置布局项目之间的间距；本行取值为 `17px`。 |
| 1019 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 1020 | <code>  padding: 19px 0;</code> | 设置内容与边框之间的内边距；本行取值为 `19px 0`。 |
| 1021 | <code>  color: #8a96a9;</code> | 设置文字颜色；本行取值为 `#8a96a9`。 |
| 1022 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 1023 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1024 | <code>.order-total strong {</code> | 选择匹配 `.order-total strong` 的页面元素，开始为它们声明 CSS 样式。 |
| 1025 | <code>  color: #3267e3;</code> | 设置文字颜色；本行取值为 `#3267e3`。 |
| 1026 | <code>  font-family: Arial, sans-serif;</code> | 指定字体及备用字体；本行取值为 `Arial, sans-serif`。 |
| 1027 | <code>  font-size: 23px;</code> | 设置字号；本行取值为 `23px`。 |
| 1028 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1029 | <code>.order-detail-heading {</code> | 选择匹配 `.order-detail-heading` 的页面元素，开始为它们声明 CSS 样式。 |
| 1030 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 1031 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 1032 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 1033 | <code>  margin-bottom: 25px;</code> | 设置下方外边距；本行取值为 `25px`。 |
| 1034 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1035 | <code>.order-detail-heading h3 {</code> | 选择匹配 `.order-detail-heading h3` 的页面元素，开始为它们声明 CSS 样式。 |
| 1036 | <code>  margin: 8px 0 0;</code> | 设置元素外侧留白；本行取值为 `8px 0 0`。 |
| 1037 | <code>  font-family: Arial, sans-serif;</code> | 指定字体及备用字体；本行取值为 `Arial, sans-serif`。 |
| 1038 | <code>  font-size: 18px;</code> | 设置字号；本行取值为 `18px`。 |
| 1039 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1040 | <code>.detail-grid {</code> | 选择匹配 `.detail-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1041 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 1042 | <code>  grid-template-columns: 1fr 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1fr 1fr`。 |
| 1043 | <code>  gap: 18px;</code> | 设置布局项目之间的间距；本行取值为 `18px`。 |
| 1044 | <code>  margin: 0 0 25px;</code> | 设置元素外侧留白；本行取值为 `0 0 25px`。 |
| 1045 | <code>  padding: 20px;</code> | 设置内容与边框之间的内边距；本行取值为 `20px`。 |
| 1046 | <code>  background: #f8fafd;</code> | 设置背景；本行取值为 `#f8fafd`。 |
| 1047 | <code>  border-radius: 6px;</code> | 设置圆角半径；本行取值为 `6px`。 |
| 1048 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1049 | <code>.detail-grid dt {</code> | 选择匹配 `.detail-grid dt` 的页面元素，开始为它们声明 CSS 样式。 |
| 1050 | <code>  color: #99a4b4;</code> | 设置文字颜色；本行取值为 `#99a4b4`。 |
| 1051 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 1052 | <code>  margin-bottom: 5px;</code> | 设置下方外边距；本行取值为 `5px`。 |
| 1053 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1054 | <code>.detail-grid dd {</code> | 选择匹配 `.detail-grid dd` 的页面元素，开始为它们声明 CSS 样式。 |
| 1055 | <code>  margin: 0;</code> | 设置元素外侧留白；本行取值为 `0`。 |
| 1056 | <code>  color: #66768e;</code> | 设置文字颜色；本行取值为 `#66768e`。 |
| 1057 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 1058 | <code>  overflow-wrap: anywhere;</code> | 允许过长内容在必要时换行；本行取值为 `anywhere`。 |
| 1059 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1060 | <code>.login-page {</code> | 选择匹配 `.login-page` 的页面元素，开始为它们声明 CSS 样式。 |
| 1061 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 1062 | <code>  grid-template-columns: 1.1fr 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1.1fr 1fr`。 |
| 1063 | <code>  min-height: 100dvh;</code> | 限制最小高度；本行取值为 `100dvh`。 |
| 1064 | <code>  background: #fff;</code> | 设置背景；本行取值为 `#fff`。 |
| 1065 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1066 | <code>.login-story {</code> | 选择匹配 `.login-story` 的页面元素，开始为它们声明 CSS 样式。 |
| 1067 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 1068 | <code>  flex-direction: column;</code> | 设置弹性布局排列方向；本行取值为 `column`。 |
| 1069 | <code>  min-height: 100dvh;</code> | 限制最小高度；本行取值为 `100dvh`。 |
| 1070 | <code>  padding: 48px 9%;</code> | 设置内容与边框之间的内边距；本行取值为 `48px 9%`。 |
| 1071 | <code>  position: relative;</code> | 设置定位方式；本行取值为 `relative`。 |
| 1072 | <code>  overflow: hidden;</code> | 设置内容溢出的处理方式；本行取值为 `hidden`。 |
| 1073 | <code>  background: linear-gradient(140deg, #edf3ff 0%, #f3f7ff 55%, #edf2fc 100%);</code> | 设置背景；本行取值为 `linear-gradient(140deg, #edf3ff 0%, #f3f7ff 55%, #edf2fc 100%)`。 |
| 1074 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1075 | <code>.login-brand {</code> | 选择匹配 `.login-brand` 的页面元素，开始为它们声明 CSS 样式。 |
| 1076 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 1077 | <code>  align-items: center;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `center`。 |
| 1078 | <code>  gap: 13px;</code> | 设置布局项目之间的间距；本行取值为 `13px`。 |
| 1079 | <code>  color: #385980;</code> | 设置文字颜色；本行取值为 `#385980`。 |
| 1080 | <code>  font-size: 17px;</code> | 设置字号；本行取值为 `17px`。 |
| 1081 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 1082 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1083 | <code>.login-brand .brand-symbol {</code> | 选择匹配 `.login-brand .brand-symbol` 的页面元素，开始为它们声明 CSS 样式。 |
| 1084 | <code>  width: 34px;</code> | 设置宽度；本行取值为 `34px`。 |
| 1085 | <code>  height: 34px;</code> | 设置高度；本行取值为 `34px`。 |
| 1086 | <code>  border-radius: 10px;</code> | 设置圆角半径；本行取值为 `10px`。 |
| 1087 | <code>  font-size: 21px;</code> | 设置字号；本行取值为 `21px`。 |
| 1088 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1089 | <code>.login-intro {</code> | 选择匹配 `.login-intro` 的页面元素，开始为它们声明 CSS 样式。 |
| 1090 | <code>  margin-top: 90px;</code> | 设置上方外边距；本行取值为 `90px`。 |
| 1091 | <code>  z-index: 1;</code> | 设置定位元素的层叠顺序；本行取值为 `1`。 |
| 1092 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1093 | <code>.login-intro .eyebrow {</code> | 选择匹配 `.login-intro .eyebrow` 的页面元素，开始为它们声明 CSS 样式。 |
| 1094 | <code>  margin-bottom: 22px;</code> | 设置下方外边距；本行取值为 `22px`。 |
| 1095 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 1096 | <code>  letter-spacing: 3px;</code> | 设置字符间距；本行取值为 `3px`。 |
| 1097 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1098 | <code>.login-intro h1 {</code> | 选择匹配 `.login-intro h1` 的页面元素，开始为它们声明 CSS 样式。 |
| 1099 | <code>  font-size: clamp(32px, 3.3vw, 49px);</code> | 设置字号；本行取值为 `clamp(32px, 3.3vw, 49px)`。 |
| 1100 | <code>  line-height: 1.45;</code> | 设置行高；本行取值为 `1.45`。 |
| 1101 | <code>  letter-spacing: 1px;</code> | 设置字符间距；本行取值为 `1px`。 |
| 1102 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 1103 | <code>  color: #375d96;</code> | 设置文字颜色；本行取值为 `#375d96`。 |
| 1104 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1105 | <code>.login-intro &gt; p:last-of-type {</code> | 选择匹配 `.login-intro > p:last-of-type` 的页面元素，开始为它们声明 CSS 样式。 |
| 1106 | <code>  margin-top: 22px;</code> | 设置上方外边距；本行取值为 `22px`。 |
| 1107 | <code>  color: #8fa1bf;</code> | 设置文字颜色；本行取值为 `#8fa1bf`。 |
| 1108 | <code>  font-size: 13px;</code> | 设置字号；本行取值为 `13px`。 |
| 1109 | <code>  line-height: 1.9;</code> | 设置行高；本行取值为 `1.9`。 |
| 1110 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1111 | <code>.login-features {</code> | 选择匹配 `.login-features` 的页面元素，开始为它们声明 CSS 样式。 |
| 1112 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 1113 | <code>  gap: 18px;</code> | 设置布局项目之间的间距；本行取值为 `18px`。 |
| 1114 | <code>  margin-top: 30px;</code> | 设置上方外边距；本行取值为 `30px`。 |
| 1115 | <code>  color: #839ac0;</code> | 设置文字颜色；本行取值为 `#839ac0`。 |
| 1116 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 1117 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1118 | <code>.login-features span::before {</code> | 选择匹配 `.login-features span::before` 的页面元素，开始为它们声明 CSS 样式。 |
| 1119 | <code>  content: &quot;✓&quot;;</code> | 为伪元素提供内容；本行取值为 `"✓"`。 |
| 1120 | <code>  margin-right: 7px;</code> | 设置右侧外边距；本行取值为 `7px`。 |
| 1121 | <code>  color: #7598d3;</code> | 设置文字颜色；本行取值为 `#7598d3`。 |
| 1122 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1123 | <code>.story-grid {</code> | 选择匹配 `.story-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1124 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 1125 | <code>  align-items: flex-end;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `flex-end`。 |
| 1126 | <code>  gap: 18px;</code> | 设置布局项目之间的间距；本行取值为 `18px`。 |
| 1127 | <code>  margin: 40px 0 55px;</code> | 设置元素外侧留白；本行取值为 `40px 0 55px`。 |
| 1128 | <code>  transform: rotate(-5deg);</code> | 对元素执行平移、缩放或旋转；本行取值为 `rotate(-5deg)`。 |
| 1129 | <code>  max-width: 430px;</code> | 限制最大宽度；本行取值为 `430px`。 |
| 1130 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1131 | <code>.story-card {</code> | 选择匹配 `.story-card` 的页面元素，开始为它们声明 CSS 样式。 |
| 1132 | <code>  width: 45%;</code> | 设置宽度；本行取值为 `45%`。 |
| 1133 | <code>  padding: 20px;</code> | 设置内容与边框之间的内边距；本行取值为 `20px`。 |
| 1134 | <code>  border: 1px solid #e6edfb;</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid #e6edfb`。 |
| 1135 | <code>  border-radius: 13px;</code> | 设置圆角半径；本行取值为 `13px`。 |
| 1136 | <code>  background: #ffffffba;</code> | 设置背景；本行取值为 `#ffffffba`。 |
| 1137 | <code>  box-shadow: 0 20px 35px #c1d0ed20;</code> | 为元素添加阴影；本行取值为 `0 20px 35px #c1d0ed20`。 |
| 1138 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1139 | <code>.story-card.tall {</code> | 选择匹配 `.story-card.tall` 的页面元素，开始为它们声明 CSS 样式。 |
| 1140 | <code>  width: 55%;</code> | 设置宽度；本行取值为 `55%`。 |
| 1141 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1142 | <code>.story-card &gt; span {</code> | 选择匹配 `.story-card > span` 的页面元素，开始为它们声明 CSS 样式。 |
| 1143 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 1144 | <code>  color: #9aadc9;</code> | 设置文字颜色；本行取值为 `#9aadc9`。 |
| 1145 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1146 | <code>.bar-chart {</code> | 选择匹配 `.bar-chart` 的页面元素，开始为它们声明 CSS 样式。 |
| 1147 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 1148 | <code>  align-items: flex-end;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `flex-end`。 |
| 1149 | <code>  gap: 9px;</code> | 设置布局项目之间的间距；本行取值为 `9px`。 |
| 1150 | <code>  height: 95px;</code> | 设置高度；本行取值为 `95px`。 |
| 1151 | <code>  padding-top: 20px;</code> | 设置顶部内边距；本行取值为 `20px`。 |
| 1152 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1153 | <code>.bar-chart i {</code> | 选择匹配 `.bar-chart i` 的页面元素，开始为它们声明 CSS 样式。 |
| 1154 | <code>  flex: 1;</code> | 设置弹性项目的伸缩与基础尺寸；本行取值为 `1`。 |
| 1155 | <code>  height: 38%;</code> | 设置高度；本行取值为 `38%`。 |
| 1156 | <code>  border-radius: 4px 4px 0 0;</code> | 设置圆角半径；本行取值为 `4px 4px 0 0`。 |
| 1157 | <code>  background: #bdcff2;</code> | 设置背景；本行取值为 `#bdcff2`。 |
| 1158 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1159 | <code>.bar-chart i:nth-child(2) {</code> | 选择匹配 `.bar-chart i:nth-child(2)` 的页面元素，开始为它们声明 CSS 样式。 |
| 1160 | <code>  height: 65%;</code> | 设置高度；本行取值为 `65%`。 |
| 1161 | <code>  background: #a9c0ed;</code> | 设置背景；本行取值为 `#a9c0ed`。 |
| 1162 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1163 | <code>.bar-chart i:nth-child(3) {</code> | 选择匹配 `.bar-chart i:nth-child(3)` 的页面元素，开始为它们声明 CSS 样式。 |
| 1164 | <code>  height: 48%;</code> | 设置高度；本行取值为 `48%`。 |
| 1165 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1166 | <code>.bar-chart i:nth-child(4) {</code> | 选择匹配 `.bar-chart i:nth-child(4)` 的页面元素，开始为它们声明 CSS 样式。 |
| 1167 | <code>  height: 77%;</code> | 设置高度；本行取值为 `77%`。 |
| 1168 | <code>  background: #91afe5;</code> | 设置背景；本行取值为 `#91afe5`。 |
| 1169 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1170 | <code>.bar-chart i:nth-child(5) {</code> | 选择匹配 `.bar-chart i:nth-child(5)` 的页面元素，开始为它们声明 CSS 样式。 |
| 1171 | <code>  height: 65%;</code> | 设置高度；本行取值为 `65%`。 |
| 1172 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1173 | <code>.bar-chart i:nth-child(6) {</code> | 选择匹配 `.bar-chart i:nth-child(6)` 的页面元素，开始为它们声明 CSS 样式。 |
| 1174 | <code>  height: 98%;</code> | 设置高度；本行取值为 `98%`。 |
| 1175 | <code>  background: #7097dd;</code> | 设置背景；本行取值为 `#7097dd`。 |
| 1176 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1177 | <code>.decor-lines {</code> | 选择匹配 `.decor-lines` 的页面元素，开始为它们声明 CSS 样式。 |
| 1178 | <code>  display: flex;</code> | 设置布局方式；本行取值为 `flex`。 |
| 1179 | <code>  flex-direction: column;</code> | 设置弹性布局排列方向；本行取值为 `column`。 |
| 1180 | <code>  gap: 12px;</code> | 设置布局项目之间的间距；本行取值为 `12px`。 |
| 1181 | <code>  margin-top: 22px;</code> | 设置上方外边距；本行取值为 `22px`。 |
| 1182 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1183 | <code>.decor-lines i {</code> | 选择匹配 `.decor-lines i` 的页面元素，开始为它们声明 CSS 样式。 |
| 1184 | <code>  height: 7px;</code> | 设置高度；本行取值为 `7px`。 |
| 1185 | <code>  border-radius: 4px;</code> | 设置圆角半径；本行取值为 `4px`。 |
| 1186 | <code>  width: 95%;</code> | 设置宽度；本行取值为 `95%`。 |
| 1187 | <code>  background: #e0e8f8;</code> | 设置背景；本行取值为 `#e0e8f8`。 |
| 1188 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1189 | <code>.decor-lines i:nth-child(2) {</code> | 选择匹配 `.decor-lines i:nth-child(2)` 的页面元素，开始为它们声明 CSS 样式。 |
| 1190 | <code>  width: 65%;</code> | 设置宽度；本行取值为 `65%`。 |
| 1191 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1192 | <code>.decor-lines i:nth-child(3) {</code> | 选择匹配 `.decor-lines i:nth-child(3)` 的页面元素，开始为它们声明 CSS 样式。 |
| 1193 | <code>  width: 80%;</code> | 设置宽度；本行取值为 `80%`。 |
| 1194 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1195 | <code>.login-copyright {</code> | 选择匹配 `.login-copyright` 的页面元素，开始为它们声明 CSS 样式。 |
| 1196 | <code>  margin-top: auto;</code> | 设置上方外边距；本行取值为 `auto`。 |
| 1197 | <code>  color: #a2b1ca;</code> | 设置文字颜色；本行取值为 `#a2b1ca`。 |
| 1198 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 1199 | <code>  letter-spacing: 2px;</code> | 设置字符间距；本行取值为 `2px`。 |
| 1200 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1201 | <code>.login-form-side {</code> | 选择匹配 `.login-form-side` 的页面元素，开始为它们声明 CSS 样式。 |
| 1202 | <code>  display: grid;</code> | 设置布局方式；本行取值为 `grid`。 |
| 1203 | <code>  place-items: center;</code> | 同时设置网格项目的水平与垂直对齐；本行取值为 `center`。 |
| 1204 | <code>  padding: 45px;</code> | 设置内容与边框之间的内边距；本行取值为 `45px`。 |
| 1205 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1206 | <code>.login-box {</code> | 选择匹配 `.login-box` 的页面元素，开始为它们声明 CSS 样式。 |
| 1207 | <code>  width: 100%;</code> | 设置宽度；本行取值为 `100%`。 |
| 1208 | <code>  max-width: 355px;</code> | 限制最大宽度；本行取值为 `355px`。 |
| 1209 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1210 | <code>.login-kicker {</code> | 选择匹配 `.login-kicker` 的页面元素，开始为它们声明 CSS 样式。 |
| 1211 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 1212 | <code>  margin-bottom: 13px;</code> | 设置下方外边距；本行取值为 `13px`。 |
| 1213 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 1214 | <code>  color: #91a0b7;</code> | 设置文字颜色；本行取值为 `#91a0b7`。 |
| 1215 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1216 | <code>.login-box h2 {</code> | 选择匹配 `.login-box h2` 的页面元素，开始为它们声明 CSS 样式。 |
| 1217 | <code>  margin-bottom: 13px;</code> | 设置下方外边距；本行取值为 `13px`。 |
| 1218 | <code>  font-size: 26px;</code> | 设置字号；本行取值为 `26px`。 |
| 1219 | <code>  letter-spacing: 0.4px;</code> | 设置字符间距；本行取值为 `0.4px`。 |
| 1220 | <code>  font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 1221 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1222 | <code>.login-box &gt; p {</code> | 选择匹配 `.login-box > p` 的页面元素，开始为它们声明 CSS 样式。 |
| 1223 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 1224 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1225 | <code>.login-box form {</code> | 选择匹配 `.login-box form` 的页面元素，开始为它们声明 CSS 样式。 |
| 1226 | <code>  margin-top: 35px;</code> | 设置上方外边距；本行取值为 `35px`。 |
| 1227 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1228 | <code>.login-box label {</code> | 选择匹配 `.login-box label` 的页面元素，开始为它们声明 CSS 样式。 |
| 1229 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 1230 | <code>  margin: 21px 0 9px;</code> | 设置元素外侧留白；本行取值为 `21px 0 9px`。 |
| 1231 | <code>  font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 1232 | <code>  color: #738198;</code> | 设置文字颜色；本行取值为 `#738198`。 |
| 1233 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1234 | <code>.login-box input {</code> | 选择匹配 `.login-box input` 的页面元素，开始为它们声明 CSS 样式。 |
| 1235 | <code>  padding: 13px 14px;</code> | 设置内容与边框之间的内边距；本行取值为 `13px 14px`。 |
| 1236 | <code>  background: #fafbfd;</code> | 设置背景；本行取值为 `#fafbfd`。 |
| 1237 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1238 | <code>.login-submit {</code> | 选择匹配 `.login-submit` 的页面元素，开始为它们声明 CSS 样式。 |
| 1239 | <code>  width: 100%;</code> | 设置宽度；本行取值为 `100%`。 |
| 1240 | <code>  margin-top: 29px;</code> | 设置上方外边距；本行取值为 `29px`。 |
| 1241 | <code>  padding: 13px 20px;</code> | 设置内容与边框之间的内边距；本行取值为 `13px 20px`。 |
| 1242 | <code>  justify-content: space-between;</code> | 设置布局项目在主轴上的对齐与分布；本行取值为 `space-between`。 |
| 1243 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1244 | <code>.login-submit span {</code> | 选择匹配 `.login-submit span` 的页面元素，开始为它们声明 CSS 样式。 |
| 1245 | <code>  font-size: 20px;</code> | 设置字号；本行取值为 `20px`。 |
| 1246 | <code>  line-height: 1;</code> | 设置行高；本行取值为 `1`。 |
| 1247 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1248 | <code>.demo-account {</code> | 选择匹配 `.demo-account` 的页面元素，开始为它们声明 CSS 样式。 |
| 1249 | <code>  margin-top: 25px;</code> | 设置上方外边距；本行取值为 `25px`。 |
| 1250 | <code>  border: 1px solid #edf0f6;</code> | 设置边框宽度、样式和颜色；本行取值为 `1px solid #edf0f6`。 |
| 1251 | <code>  border-radius: 6px;</code> | 设置圆角半径；本行取值为 `6px`。 |
| 1252 | <code>  padding: 13px 15px;</code> | 设置内容与边框之间的内边距；本行取值为 `13px 15px`。 |
| 1253 | <code>  background: #fafbfd;</code> | 设置背景；本行取值为 `#fafbfd`。 |
| 1254 | <code>  color: #94a0b2;</code> | 设置文字颜色；本行取值为 `#94a0b2`。 |
| 1255 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 1256 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1257 | <code>.demo-account summary {</code> | 选择匹配 `.demo-account summary` 的页面元素，开始为它们声明 CSS 样式。 |
| 1258 | <code>  cursor: pointer;</code> | 设置鼠标指针样式；本行取值为 `pointer`。 |
| 1259 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1260 | <code>.demo-account p {</code> | 选择匹配 `.demo-account p` 的页面元素，开始为它们声明 CSS 样式。 |
| 1261 | <code>  margin: 9px 0 0;</code> | 设置元素外侧留白；本行取值为 `9px 0 0`。 |
| 1262 | <code>  font-family: monospace;</code> | 指定字体及备用字体；本行取值为 `monospace`。 |
| 1263 | <code>  font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 1264 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1265 | <code>.demo-account small {</code> | 选择匹配 `.demo-account small` 的页面元素，开始为它们声明 CSS 样式。 |
| 1266 | <code>  display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 1267 | <code>  margin-top: 10px;</code> | 设置上方外边距；本行取值为 `10px`。 |
| 1268 | <code>  font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 1269 | <code>  color: #b29c83;</code> | 设置文字颜色；本行取值为 `#b29c83`。 |
| 1270 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1271 | <code>.login-note {</code> | 选择匹配 `.login-note` 的页面元素，开始为它们声明 CSS 样式。 |
| 1272 | <code>  margin: 33px 0 0;</code> | 设置元素外侧留白；本行取值为 `33px 0 0`。 |
| 1273 | <code>  text-align: center;</code> | 设置行内内容水平对齐；本行取值为 `center`。 |
| 1274 | <code>  font-size: 10px !important;</code> | 设置字号；本行取值为 `10px !important`。 |
| 1275 | <code>  color: #b0b8c6;</code> | 设置文字颜色；本行取值为 `#b0b8c6`。 |
| 1276 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1277 | <code>@media (min-width: 1600px) {</code> | 定义媒体查询 @media (min-width: 1600px) {，只在设备/视口满足条件时应用后续样式。 |
| 1278 | <code>  .page-content {</code> | 选择匹配 `.page-content` 的页面元素，开始为它们声明 CSS 样式。 |
| 1279 | <code>    padding-top: 42px;</code> | 设置顶部内边距；本行取值为 `42px`。 |
| 1280 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1281 | <code>  .stats-grid {</code> | 选择匹配 `.stats-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1282 | <code>    gap: 25px;</code> | 设置布局项目之间的间距；本行取值为 `25px`。 |
| 1283 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1284 | <code>  .stat-card {</code> | 选择匹配 `.stat-card` 的页面元素，开始为它们声明 CSS 样式。 |
| 1285 | <code>    padding: 27px;</code> | 设置内容与边框之间的内边距；本行取值为 `27px`。 |
| 1286 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1287 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1288 | <code>@media (max-width: 1200px) {</code> | 定义媒体查询 @media (max-width: 1200px) {，只在设备/视口满足条件时应用后续样式。 |
| 1289 | <code>  .sidebar {</code> | 选择匹配 `.sidebar` 的页面元素，开始为它们声明 CSS 样式。 |
| 1290 | <code>    width: 205px;</code> | 设置宽度；本行取值为 `205px`。 |
| 1291 | <code>    padding-inline: 13px;</code> | 设置文字行方向两侧的内边距，在默认中文横排中为左右内边距；本行取值为 `13px`。 |
| 1292 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1293 | <code>  .workspace {</code> | 选择匹配 `.workspace` 的页面元素，开始为它们声明 CSS 样式。 |
| 1294 | <code>    margin-left: 205px;</code> | 设置左侧外边距；本行取值为 `205px`。 |
| 1295 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1296 | <code>  .topbar {</code> | 选择匹配 `.topbar` 的页面元素，开始为它们声明 CSS 样式。 |
| 1297 | <code>    padding-inline: 25px;</code> | 设置文字行方向两侧的内边距，在默认中文横排中为左右内边距；本行取值为 `25px`。 |
| 1298 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1299 | <code>  .page-content {</code> | 选择匹配 `.page-content` 的页面元素，开始为它们声明 CSS 样式。 |
| 1300 | <code>    padding: 28px 25px;</code> | 设置内容与边框之间的内边距；本行取值为 `28px 25px`。 |
| 1301 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1302 | <code>  .stats-grid {</code> | 选择匹配 `.stats-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1303 | <code>    gap: 14px;</code> | 设置布局项目之间的间距；本行取值为 `14px`。 |
| 1304 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1305 | <code>  .stat-card {</code> | 选择匹配 `.stat-card` 的页面元素，开始为它们声明 CSS 样式。 |
| 1306 | <code>    padding: 20px 16px;</code> | 设置内容与边框之间的内边距；本行取值为 `20px 16px`。 |
| 1307 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1308 | <code>  .stat-card strong {</code> | 选择匹配 `.stat-card strong` 的页面元素，开始为它们声明 CSS 样式。 |
| 1309 | <code>    font-size: 23px;</code> | 设置字号；本行取值为 `23px`。 |
| 1310 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1311 | <code>  .stat-symbol {</code> | 选择匹配 `.stat-symbol` 的页面元素，开始为它们声明 CSS 样式。 |
| 1312 | <code>    width: 30px;</code> | 设置宽度；本行取值为 `30px`。 |
| 1313 | <code>    height: 30px;</code> | 设置高度；本行取值为 `30px`。 |
| 1314 | <code>    font-size: 17px;</code> | 设置字号；本行取值为 `17px`。 |
| 1315 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1316 | <code>  .dashboard-grid {</code> | 选择匹配 `.dashboard-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1317 | <code>    grid-template-columns: 1.3fr 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1.3fr 1fr`。 |
| 1318 | <code>    gap: 18px;</code> | 设置布局项目之间的间距；本行取值为 `18px`。 |
| 1319 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1320 | <code>  .panel-header,</code> | 并列 CSS 选择器 `.panel-header`：与后续选择器共用同一组样式。 |
| 1321 | <code>  .toolbar {</code> | 选择匹配 `.toolbar` 的页面元素，开始为它们声明 CSS 样式。 |
| 1322 | <code>    padding: 20px;</code> | 设置内容与边框之间的内边距；本行取值为 `20px`。 |
| 1323 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1324 | <code>  td,</code> | 并列 CSS 选择器 `td`：与后续选择器共用同一组样式。 |
| 1325 | <code>  th {</code> | 选择匹配 `th` 的页面元素，开始为它们声明 CSS 样式。 |
| 1326 | <code>    padding-inline: 17px;</code> | 设置文字行方向两侧的内边距，在默认中文横排中为左右内边距；本行取值为 `17px`。 |
| 1327 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1328 | <code>  .login-story {</code> | 选择匹配 `.login-story` 的页面元素，开始为它们声明 CSS 样式。 |
| 1329 | <code>    padding-inline: 8%;</code> | 设置文字行方向两侧的内边距，在默认中文横排中为左右内边距；本行取值为 `8%`。 |
| 1330 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1331 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1332 | <code>@media (max-width: 1000px) {</code> | 定义媒体查询 @media (max-width: 1000px) {，只在设备/视口满足条件时应用后续样式。 |
| 1333 | <code>  .stats-grid {</code> | 选择匹配 `.stats-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1334 | <code>    grid-template-columns: repeat(2, minmax(0, 1fr));</code> | 定义网格列数与各列宽度；本行取值为 `repeat(2, minmax(0, 1fr))`。 |
| 1335 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1336 | <code>  .dashboard-grid {</code> | 选择匹配 `.dashboard-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1337 | <code>    grid-template-columns: 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1fr`。 |
| 1338 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1339 | <code>  .filter-bar {</code> | 选择匹配 `.filter-bar` 的页面元素，开始为它们声明 CSS 样式。 |
| 1340 | <code>    flex-wrap: wrap;</code> | 设置弹性项目是否换行；本行取值为 `wrap`。 |
| 1341 | <code>    padding-inline: 20px;</code> | 设置文字行方向两侧的内边距，在默认中文横排中为左右内边距；本行取值为 `20px`。 |
| 1342 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1343 | <code>  .search-input {</code> | 选择匹配 `.search-input` 的页面元素，开始为它们声明 CSS 样式。 |
| 1344 | <code>    max-width: none;</code> | 限制最大宽度；本行取值为 `none`。 |
| 1345 | <code>    flex-basis: 100%;</code> | 设置弹性项目开始分配空间前的基础尺寸；本行取值为 `100%`。 |
| 1346 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1347 | <code>  .filter-bar select {</code> | 选择匹配 `.filter-bar select` 的页面元素，开始为它们声明 CSS 样式。 |
| 1348 | <code>    flex: 1;</code> | 设置弹性项目的伸缩与基础尺寸；本行取值为 `1`。 |
| 1349 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1350 | <code>  .login-features {</code> | 选择匹配 `.login-features` 的页面元素，开始为它们声明 CSS 样式。 |
| 1351 | <code>    gap: 11px;</code> | 设置布局项目之间的间距；本行取值为 `11px`。 |
| 1352 | <code>    font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 1353 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1354 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1355 | <code>@media (max-width: 760px) {</code> | 定义媒体查询 @media (max-width: 760px) {，只在设备/视口满足条件时应用后续样式。 |
| 1356 | <code>  .sidebar {</code> | 选择匹配 `.sidebar` 的页面元素，开始为它们声明 CSS 样式。 |
| 1357 | <code>    width: 228px;</code> | 设置宽度；本行取值为 `228px`。 |
| 1358 | <code>    transform: translateX(-100%);</code> | 对元素执行平移、缩放或旋转；本行取值为 `translateX(-100%)`。 |
| 1359 | <code>    transition: transform 0.2s;</code> | 设置属性变化时的过渡效果；本行取值为 `transform 0.2s`。 |
| 1360 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1361 | <code>  .sidebar.open {</code> | 选择匹配 `.sidebar.open` 的页面元素，开始为它们声明 CSS 样式。 |
| 1362 | <code>    transform: translateX(0);</code> | 对元素执行平移、缩放或旋转；本行取值为 `translateX(0)`。 |
| 1363 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1364 | <code>  .sidebar-shade {</code> | 选择匹配 `.sidebar-shade` 的页面元素，开始为它们声明 CSS 样式。 |
| 1365 | <code>    position: fixed;</code> | 设置定位方式；本行取值为 `fixed`。 |
| 1366 | <code>    inset: 0;</code> | 同时设置四边定位距离；本行取值为 `0`。 |
| 1367 | <code>    z-index: 25;</code> | 设置定位元素的层叠顺序；本行取值为 `25`。 |
| 1368 | <code>    background: #1e2d4560;</code> | 设置背景；本行取值为 `#1e2d4560`。 |
| 1369 | <code>    border: 0;</code> | 设置边框宽度、样式和颜色；本行取值为 `0`。 |
| 1370 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1371 | <code>  .workspace {</code> | 选择匹配 `.workspace` 的页面元素，开始为它们声明 CSS 样式。 |
| 1372 | <code>    margin-left: 0;</code> | 设置左侧外边距；本行取值为 `0`。 |
| 1373 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1374 | <code>  .mobile-menu {</code> | 选择匹配 `.mobile-menu` 的页面元素，开始为它们声明 CSS 样式。 |
| 1375 | <code>    display: inline-flex;</code> | 设置布局方式；本行取值为 `inline-flex`。 |
| 1376 | <code>    width: 25px;</code> | 设置宽度；本行取值为 `25px`。 |
| 1377 | <code>    height: 25px;</code> | 设置高度；本行取值为 `25px`。 |
| 1378 | <code>    font-size: 18px;</code> | 设置字号；本行取值为 `18px`。 |
| 1379 | <code>    color: #6f7f96;</code> | 设置文字颜色；本行取值为 `#6f7f96`。 |
| 1380 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1381 | <code>  .topbar {</code> | 选择匹配 `.topbar` 的页面元素，开始为它们声明 CSS 样式。 |
| 1382 | <code>    height: 65px;</code> | 设置高度；本行取值为 `65px`。 |
| 1383 | <code>    padding-inline: 18px;</code> | 设置文字行方向两侧的内边距，在默认中文横排中为左右内边距；本行取值为 `18px`。 |
| 1384 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1385 | <code>  .breadcrumb {</code> | 选择匹配 `.breadcrumb` 的页面元素，开始为它们声明 CSS 样式。 |
| 1386 | <code>    gap: 10px;</code> | 设置布局项目之间的间距；本行取值为 `10px`。 |
| 1387 | <code>    font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 1388 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1389 | <code>  .account {</code> | 选择匹配 `.account` 的页面元素，开始为它们声明 CSS 样式。 |
| 1390 | <code>    gap: 7px;</code> | 设置布局项目之间的间距；本行取值为 `7px`。 |
| 1391 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1392 | <code>  .account &gt; div {</code> | 选择匹配 `.account > div` 的页面元素，开始为它们声明 CSS 样式。 |
| 1393 | <code>    display: none;</code> | 设置布局方式；本行取值为 `none`。 |
| 1394 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1395 | <code>  .logout-button {</code> | 选择匹配 `.logout-button` 的页面元素，开始为它们声明 CSS 样式。 |
| 1396 | <code>    padding-left: 10px;</code> | 设置左侧内边距；本行取值为 `10px`。 |
| 1397 | <code>    margin-left: 4px;</code> | 设置左侧外边距；本行取值为 `4px`。 |
| 1398 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1399 | <code>  .page-content {</code> | 选择匹配 `.page-content` 的页面元素，开始为它们声明 CSS 样式。 |
| 1400 | <code>    padding: 25px 17px;</code> | 设置内容与边框之间的内边距；本行取值为 `25px 17px`。 |
| 1401 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1402 | <code>  .page-heading {</code> | 选择匹配 `.page-heading` 的页面元素，开始为它们声明 CSS 样式。 |
| 1403 | <code>    gap: 12px;</code> | 设置布局项目之间的间距；本行取值为 `12px`。 |
| 1404 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1405 | <code>  h1 {</code> | 选择匹配 `h1` 的页面元素，开始为它们声明 CSS 样式。 |
| 1406 | <code>    font-size: 23px;</code> | 设置字号；本行取值为 `23px`。 |
| 1407 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1408 | <code>  .page-heading p {</code> | 选择匹配 `.page-heading p` 的页面元素，开始为它们声明 CSS 样式。 |
| 1409 | <code>    font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 1410 | <code>    line-height: 1.7;</code> | 设置行高；本行取值为 `1.7`。 |
| 1411 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1412 | <code>  .environment-tag {</code> | 选择匹配 `.environment-tag` 的页面元素，开始为它们声明 CSS 样式。 |
| 1413 | <code>    display: none;</code> | 设置布局方式；本行取值为 `none`。 |
| 1414 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1415 | <code>  .stats-grid {</code> | 选择匹配 `.stats-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1416 | <code>    gap: 11px;</code> | 设置布局项目之间的间距；本行取值为 `11px`。 |
| 1417 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1418 | <code>  .stat-card {</code> | 选择匹配 `.stat-card` 的页面元素，开始为它们声明 CSS 样式。 |
| 1419 | <code>    padding: 19px 14px;</code> | 设置内容与边框之间的内边距；本行取值为 `19px 14px`。 |
| 1420 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1421 | <code>  .stat-card small {</code> | 选择匹配 `.stat-card small` 的页面元素，开始为它们声明 CSS 样式。 |
| 1422 | <code>    font-size: 9px;</code> | 设置字号；本行取值为 `9px`。 |
| 1423 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1424 | <code>  .welcome-banner {</code> | 选择匹配 `.welcome-banner` 的页面元素，开始为它们声明 CSS 样式。 |
| 1425 | <code>    padding: 22px;</code> | 设置内容与边框之间的内边距；本行取值为 `22px`。 |
| 1426 | <code>    flex-direction: column;</code> | 设置弹性布局排列方向；本行取值为 `column`。 |
| 1427 | <code>    align-items: flex-start;</code> | 设置布局项目在交叉轴上的对齐方式；本行取值为 `flex-start`。 |
| 1428 | <code>    gap: 17px;</code> | 设置布局项目之间的间距；本行取值为 `17px`。 |
| 1429 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1430 | <code>  .welcome-banner h2 {</code> | 选择匹配 `.welcome-banner h2` 的页面元素，开始为它们声明 CSS 样式。 |
| 1431 | <code>    font-size: 15px;</code> | 设置字号；本行取值为 `15px`。 |
| 1432 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1433 | <code>  .toolbar {</code> | 选择匹配 `.toolbar` 的页面元素，开始为它们声明 CSS 样式。 |
| 1434 | <code>    padding: 18px;</code> | 设置内容与边框之间的内边距；本行取值为 `18px`。 |
| 1435 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1436 | <code>  .toolbar p {</code> | 选择匹配 `.toolbar p` 的页面元素，开始为它们声明 CSS 样式。 |
| 1437 | <code>    font-size: 10px;</code> | 设置字号；本行取值为 `10px`。 |
| 1438 | <code>    max-width: 200px;</code> | 限制最大宽度；本行取值为 `200px`。 |
| 1439 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1440 | <code>  .toolbar .button {</code> | 选择匹配 `.toolbar .button` 的页面元素，开始为它们声明 CSS 样式。 |
| 1441 | <code>    padding: 9px 12px;</code> | 设置内容与边框之间的内边距；本行取值为 `9px 12px`。 |
| 1442 | <code>    font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 1443 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1444 | <code>  .filter-bar {</code> | 选择匹配 `.filter-bar` 的页面元素，开始为它们声明 CSS 样式。 |
| 1445 | <code>    gap: 9px;</code> | 设置布局项目之间的间距；本行取值为 `9px`。 |
| 1446 | <code>    padding: 0 18px 18px;</code> | 设置内容与边框之间的内边距；本行取值为 `0 18px 18px`。 |
| 1447 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1448 | <code>  .filter-bar select {</code> | 选择匹配 `.filter-bar select` 的页面元素，开始为它们声明 CSS 样式。 |
| 1449 | <code>    min-width: 110px;</code> | 限制最小宽度；本行取值为 `110px`。 |
| 1450 | <code>    width: auto;</code> | 设置宽度；本行取值为 `auto`。 |
| 1451 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1452 | <code>  .filter-bar &gt; .button {</code> | 选择匹配 `.filter-bar > .button` 的页面元素，开始为它们声明 CSS 样式。 |
| 1453 | <code>    padding: 9px 14px;</code> | 设置内容与边框之间的内边距；本行取值为 `9px 14px`。 |
| 1454 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1455 | <code>  .pagination {</code> | 选择匹配 `.pagination` 的页面元素，开始为它们声明 CSS 样式。 |
| 1456 | <code>    gap: 12px;</code> | 设置布局项目之间的间距；本行取值为 `12px`。 |
| 1457 | <code>    padding: 16px;</code> | 设置内容与边框之间的内边距；本行取值为 `16px`。 |
| 1458 | <code>    flex-wrap: wrap;</code> | 设置弹性项目是否换行；本行取值为 `wrap`。 |
| 1459 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1460 | <code>  .pagination &gt; div {</code> | 选择匹配 `.pagination > div` 的页面元素，开始为它们声明 CSS 样式。 |
| 1461 | <code>    gap: 10px;</code> | 设置布局项目之间的间距；本行取值为 `10px`。 |
| 1462 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1463 | <code>  .modal-backdrop {</code> | 选择匹配 `.modal-backdrop` 的页面元素，开始为它们声明 CSS 样式。 |
| 1464 | <code>    padding: 14px;</code> | 设置内容与边框之间的内边距；本行取值为 `14px`。 |
| 1465 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1466 | <code>  .modal {</code> | 选择匹配 `.modal` 的页面元素，开始为它们声明 CSS 样式。 |
| 1467 | <code>    max-height: calc(100dvh - 28px);</code> | 限制最大高度；本行取值为 `calc(100dvh - 28px)`。 |
| 1468 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1469 | <code>  .modal-header {</code> | 选择匹配 `.modal-header` 的页面元素，开始为它们声明 CSS 样式。 |
| 1470 | <code>    padding: 17px 20px;</code> | 设置内容与边框之间的内边距；本行取值为 `17px 20px`。 |
| 1471 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1472 | <code>  .modal-body {</code> | 选择匹配 `.modal-body` 的页面元素，开始为它们声明 CSS 样式。 |
| 1473 | <code>    padding: 20px;</code> | 设置内容与边框之间的内边距；本行取值为 `20px`。 |
| 1474 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1475 | <code>  .form-grid {</code> | 选择匹配 `.form-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1476 | <code>    gap: 0 14px;</code> | 设置布局项目之间的间距；本行取值为 `0 14px`。 |
| 1477 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1478 | <code>  .login-page {</code> | 选择匹配 `.login-page` 的页面元素，开始为它们声明 CSS 样式。 |
| 1479 | <code>    grid-template-columns: 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1fr`。 |
| 1480 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1481 | <code>  .login-story {</code> | 选择匹配 `.login-story` 的页面元素，开始为它们声明 CSS 样式。 |
| 1482 | <code>    display: none;</code> | 设置布局方式；本行取值为 `none`。 |
| 1483 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1484 | <code>  .login-form-side {</code> | 选择匹配 `.login-form-side` 的页面元素，开始为它们声明 CSS 样式。 |
| 1485 | <code>    padding: 35px 25px;</code> | 设置内容与边框之间的内边距；本行取值为 `35px 25px`。 |
| 1486 | <code>    min-height: 100dvh;</code> | 限制最小高度；本行取值为 `100dvh`。 |
| 1487 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1488 | <code>  .login-box::before {</code> | 选择匹配 `.login-box::before` 的页面元素，开始为它们声明 CSS 样式。 |
| 1489 | <code>    content: &quot;我的商城管理系统&quot;;</code> | 为伪元素提供内容；本行取值为 `"我的商城管理系统"`。 |
| 1490 | <code>    display: block;</code> | 设置布局方式；本行取值为 `block`。 |
| 1491 | <code>    color: #3267e3;</code> | 设置文字颜色；本行取值为 `#3267e3`。 |
| 1492 | <code>    font-size: 16px;</code> | 设置字号；本行取值为 `16px`。 |
| 1493 | <code>    font-weight: 600;</code> | 设置字重；本行取值为 `600`。 |
| 1494 | <code>    margin-bottom: 50px;</code> | 设置下方外边距；本行取值为 `50px`。 |
| 1495 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1496 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1497 | <code>@media (max-width: 420px) {</code> | 定义媒体查询 @media (max-width: 420px) {，只在设备/视口满足条件时应用后续样式。 |
| 1498 | <code>  .stat-card strong {</code> | 选择匹配 `.stat-card strong` 的页面元素，开始为它们声明 CSS 样式。 |
| 1499 | <code>    font-size: 21px;</code> | 设置字号；本行取值为 `21px`。 |
| 1500 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1501 | <code>  .stat-symbol {</code> | 选择匹配 `.stat-symbol` 的页面元素，开始为它们声明 CSS 样式。 |
| 1502 | <code>    display: none;</code> | 设置布局方式；本行取值为 `none`。 |
| 1503 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1504 | <code>  .form-grid {</code> | 选择匹配 `.form-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1505 | <code>    grid-template-columns: 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1fr`。 |
| 1506 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1507 | <code>  .detail-grid {</code> | 选择匹配 `.detail-grid` 的页面元素，开始为它们声明 CSS 样式。 |
| 1508 | <code>    grid-template-columns: 1fr;</code> | 定义网格列数与各列宽度；本行取值为 `1fr`。 |
| 1509 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1510 | <code>  .order-items {</code> | 选择匹配 `.order-items` 的页面元素，开始为它们声明 CSS 样式。 |
| 1511 | <code>    font-size: 11px;</code> | 设置字号；本行取值为 `11px`。 |
| 1512 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1513 | <code>  .order-product-search .button {</code> | 选择匹配 `.order-product-search .button` 的页面元素，开始为它们声明 CSS 样式。 |
| 1514 | <code>    padding-inline: 10px;</code> | 设置文字行方向两侧的内边距，在默认中文横排中为左右内边距；本行取值为 `10px`。 |
| 1515 | <code>    font-size: 12px;</code> | 设置字号；本行取值为 `12px`。 |
| 1516 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1517 | <code>  .order-items th,</code> | 并列 CSS 选择器 `.order-items th`：与后续选择器共用同一组样式。 |
| 1518 | <code>  .order-items td {</code> | 选择匹配 `.order-items td` 的页面元素，开始为它们声明 CSS 样式。 |
| 1519 | <code>    padding-inline: 10px;</code> | 设置文字行方向两侧的内边距，在默认中文横排中为左右内边距；本行取值为 `10px`。 |
| 1520 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 1521 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
