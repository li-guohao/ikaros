"use strict";(self["webpackChunkvue_antd_pro"]=self["webpackChunkvue_antd_pro"]||[]).push([[348],{75348:function(t,e,n){n.r(e),n.d(e,{default:function(){return g}});var a=function(){var t=this,e=t._self._c;return e("page-header-wrapper",{staticClass:"page-header-index-wide page-header-wrapper-grid-content-main",attrs:{title:!1}},[e("a-row",{attrs:{gutter:24}},[e("a-col",{attrs:{md:24,lg:7}},[e("a-card",{attrs:{bordered:!1}},[e("div",{staticClass:"account-center-avatarHolder"},[e("div",{staticClass:"avatar"},[e("img",{attrs:{src:t.userInfo.avatar}})]),e("div",{staticClass:"username"},[t._v(t._s(t.userInfo.nickname))]),e("div",{staticClass:"bio"},[t._v(t._s(t.userInfo.introduce))])])])],1),e("a-col",{attrs:{md:24,lg:17}},[t._v(" 右边的内容 ")])],1)],1)},r=[],i=n(45957),s=n(95082),o=(n(57327),n(41539),n(26699),n(32023),n(92222),n(88170)),u=n(20629),c=n(92795),l={components:{RouteView:o.Ws,PageView:o.B4},data:function(){return{tags:["很有想法的","专注设计","辣~","大长腿","川妹子","海纳百川"],tagInputVisible:!1,tagInputValue:"",teams:[],teamSpinning:!0,tabListNoTitle:[{key:"article",tab:"文章(8)"},{key:"app",tab:"应用(8)"},{key:"project",tab:"项目(8)"}],noTitleKey:"app"}},computed:(0,s.Z)({},(0,u.Se)(["userInfo"])),mounted:function(){this.currentUser=c.Z.getters.userInfo},methods:{handleTabChange:function(t,e){this[e]=t},handleTagClose:function(t){var e=this.tags.filter((function(e){return e!==t}));this.tags=e},showTagInput:function(){var t=this;this.tagInputVisible=!0,this.$nextTick((function(){t.$refs.tagInput.focus()}))},handleInputChange:function(t){this.tagInputValue=t.target.value},handleTagInputConfirm:function(){var t=this.tagInputValue,e=this.tags;t&&!e.includes(t)&&(e=[].concat((0,i.Z)(e),[t])),Object.assign(this,{tags:e,tagInputVisible:!1,tagInputValue:""})}}},f=l,p=n(1001),d=(0,p.Z)(f,a,r,!1,null,"23a3157d",null),g=d.exports},45957:function(t,e,n){function a(t,e){(null==e||e>t.length)&&(e=t.length);for(var n=0,a=new Array(e);n<e;n++)a[n]=t[n];return a}function r(t){if(Array.isArray(t))return a(t)}n.d(e,{Z:function(){return u}});n(82526),n(41817),n(41539),n(32165),n(78783),n(33948),n(91038);function i(t){if("undefined"!==typeof Symbol&&null!=t[Symbol.iterator]||null!=t["@@iterator"])return Array.from(t)}n(47042),n(68309),n(74916);function s(t,e){if(t){if("string"===typeof t)return a(t,e);var n=Object.prototype.toString.call(t).slice(8,-1);return"Object"===n&&t.constructor&&(n=t.constructor.name),"Map"===n||"Set"===n?Array.from(t):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?a(t,e):void 0}}function o(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function u(t){return r(t)||i(t)||s(t)||o()}}}]);