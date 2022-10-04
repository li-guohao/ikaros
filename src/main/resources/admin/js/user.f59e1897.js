"use strict";(self["webpackChunkvue_antd_pro"]=self["webpackChunkvue_antd_pro"]||[]).push([[378],{96837:function(e,t,s){s.r(t),s.d(t,{default:function(){return b}});var i=function(){var e=this,t=e._self._c;return t("div",{staticClass:"main"},[t("a-form",{ref:"formLogin",staticClass:"user-layout-login",attrs:{id:"formLogin",form:e.form},on:{submit:e.handleSubmit}},[t("a-form-item",[t("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["username",{rules:[{required:!0,message:e.$t("user.userName.required")},{validator:e.handleUsernameOrEmail}],validateTrigger:"change"}],expression:"[\n          'username',\n          {\n            rules: [{ required: true, message: $t('user.userName.required') }, { validator: handleUsernameOrEmail }],\n            validateTrigger: 'change',\n          },\n        ]"}],attrs:{size:"large",type:"text",placeholder:e.$t("user.login.username.placeholder")}},[t("a-icon",{style:{color:"rgba(0,0,0,.25)"},attrs:{slot:"prefix",type:"user"},slot:"prefix"})],1)],1),t("a-form-item",[t("a-input-password",{directives:[{name:"decorator",rawName:"v-decorator",value:["password",{rules:[{required:!0,message:e.$t("user.password.required")}],validateTrigger:"blur"}],expression:"[\n          'password',\n          { rules: [{ required: true, message: $t('user.password.required') }], validateTrigger: 'blur' },\n        ]"}],attrs:{size:"large",placeholder:e.$t("user.login.password.placeholder")}},[t("a-icon",{style:{color:"rgba(0,0,0,.25)"},attrs:{slot:"prefix",type:"lock"},slot:"prefix"})],1)],1),t("a-form-item",{staticStyle:{"margin-top":"24px"}},[t("a-button",{staticClass:"login-button",attrs:{size:"large",type:"primary",htmlType:"submit",loading:e.state.loginBtn,disabled:e.state.loginBtn}},[e._v(e._s(e.$t("user.login.login")))])],1),e.showForgetRoutLink?t("router-link",{staticClass:"forge-password",staticStyle:{float:"right"},attrs:{to:{name:"ResetPassword"}}},[e._v(e._s(e.$t("user.login.forgot-password")))]):e._e()],1)],1)},n=[],a=s(95082),r=(s(74916),s(41539),function(){var e=this,t=this,s=t._self._c;return s("a-modal",{attrs:{centered:"",maskClosable:!1},on:{cancel:t.handleCancel},model:{value:t.visible,callback:function(e){t.visible=e},expression:"visible"}},[s("div",{style:{textAlign:"center"},attrs:{slot:"title"},slot:"title"},[t._v("两步验证")]),s("template",{slot:"footer"},[s("div",{style:{textAlign:"center"}},[s("a-button",{key:"back",on:{click:t.handleCancel}},[t._v("返回")]),s("a-button",{key:"submit",attrs:{type:"primary",loading:t.stepLoading},on:{click:t.handleStepOk}},[t._v(" 继续 ")])],1)]),s("a-spin",{attrs:{spinning:t.stepLoading}},[s("a-form",{attrs:{layout:"vertical","auto-form-create":function(t){e.form=t}}},[s("div",{staticClass:"step-form-wrapper"},[t.stepLoading?s("p",{staticStyle:{"text-align":"center"}},[t._v("正在验证.."),s("br"),t._v("请稍后")]):s("p",{staticStyle:{"text-align":"center"}},[t._v("请在手机中打开 Google Authenticator 或两步验证 APP"),s("br"),t._v("输入 6 位动态码")]),s("a-form-item",{style:{textAlign:"center"},attrs:{hasFeedback:"",fieldDecoratorId:"stepCode",fieldDecoratorOptions:{rules:[{required:!0,message:"请输入 6 位动态码!",pattern:/^\d{6}$/,len:6}]}}},[s("a-input",{style:{textAlign:"center"},attrs:{placeholder:"000000"},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleStepOk.apply(null,arguments)}}})],1),s("p",{staticStyle:{"text-align":"center"}},[s("a",{on:{click:t.onForgeStepCode}},[t._v("遗失手机?")])])],1)])],1)],2)}),o=[],l={props:{visible:{type:Boolean,default:!1}},data:function(){return{stepLoading:!1,form:null}},methods:{handleStepOk:function(){var e=this,t=this;this.stepLoading=!0,this.form.validateFields((function(s,i){s?(e.stepLoading=!1,e.$emit("error",{err:s})):setTimeout((function(){t.stepLoading=!1,t.$emit("success",{values:i})}),2e3)}))},handleCancel:function(){this.visible=!1,this.$emit("cancel")},onForgeStepCode:function(){}}},c=l,u=s(1001),d=(0,u.Z)(c,r,o,!1,null,"c42b46b6",null),p=d.exports,g=s(20629),m=s(84722),f=s(12223),h={components:{TwoStepCaptcha:p},data:function(){return{customActiveKey:"tab1",loginBtn:!1,loginType:0,requiredTwoStepCaptcha:!1,stepCaptchaVisible:!1,form:this.$form.createForm(this),state:{time:60,loginBtn:!1,loginType:0,smsSendBtn:!1},showForgetRoutLink:!1,userLoginSuccessMessage:this.$t("user.login.success.message"),userLoginSuccessDescription:this.$t("user.login.success.description"),userLoginReqfailMessage:this.$t("user.login.reqfail.message")}},beforeMount:function(){document.addEventListener("keydown",this.onRegisterResetPasswordKeydown)},beforeDestroy:function(){document.removeEventListener("keydown",this.onRegisterResetPasswordKeydown)},methods:(0,a.Z)((0,a.Z)({},(0,g.nv)(["Login","Logout"])),{},{handleUsernameOrEmail:function(e,t,s){var i=this.state,n=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;n.test(t)?i.loginType=0:i.loginType=1,s()},handleTabClick:function(e){this.customActiveKey=e},handleSubmit:function(e){var t=this;e.preventDefault();var s=this.form.validateFields,i=this.state,n=this.customActiveKey,r=this.Login;i.loginBtn=!0;var o="tab1"===n?["username","password"]:["mobile","captcha"];s(o,{force:!0},(function(e,s){if(e)setTimeout((function(){i.loginBtn=!1}),600);else{var n=(0,a.Z)({},s);delete n.username,n[i.loginType?"username":"email"]=s.username,n.password=s.password,r(n).then((function(e){t.loginSuccess(e)})).catch((function(e){t.requestFailed(e)})).finally((function(){i.loginBtn=!1}))}}))},getCaptcha:function(e){var t=this;e.preventDefault();var s=this.form.validateFields,i=this.state;s(["mobile"],{force:!0},(function(e,s){if(!e){i.smsSendBtn=!0;var n=window.setInterval((function(){i.time--<=0&&(i.time=60,i.smsSendBtn=!1,window.clearInterval(n))}),1e3),a=t.$message.loading("验证码发送中..",0);(0,f.EK)({mobile:s.mobile}).then((function(e){setTimeout(a,2500),t.$notification["success"]({message:"提示",description:"验证码获取成功，您的验证码为："+e.result.captcha,duration:8})})).catch((function(e){setTimeout(a,1),clearInterval(n),i.time=60,i.smsSendBtn=!1,t.requestFailed(e)}))}}))},stepCaptchaSuccess:function(){this.loginSuccess()},stepCaptchaCancel:function(){var e=this;this.Logout().then((function(){e.loginBtn=!1,e.stepCaptchaVisible=!1}))},loginSuccess:function(e){var t=this;this.$router.push({path:"/"}),setTimeout((function(){t.$notification.success({message:t.userLoginSuccessMessage,description:"".concat((0,m.D$)())+t.userLoginSuccessDescription})}),1e3)},requestFailed:function(e){this.$notification["error"]({message:this.userLoginReqfailMessage,description:""+e,duration:4})},onRegisterResetPasswordKeydown:function(e){72===e.keyCode&&(e.altKey||e.metaKey)&&e.shiftKey&&(e.preventDefault(),this.showForgetRoutLink=!this.showForgetRoutLink)}})},v=h,y=(0,u.Z)(v,i,n,!1,null,"2fb60ca3",null),b=y.exports}}]);