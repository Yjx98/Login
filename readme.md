### 登陆注册界面 APP

#### （一）项目功能

* 登陆界面
  * 登陆验证输入的信息是否正确；
    * 信息正确：跳转首页；同时监听首页退回到当前页面的数据回传；
    * 信息错误：输出提示信息。
  * 注册界面：进行信息正确性验证；
    * 信息正确
      * 信息存储，包括存储用户信息至本地 SharedPreferences（信息存储采用键值对的方式）；
      * 信息回传：通过 Bundle 实现数据回传。
    * 信息不符合要求
      * 输出提示信息。
    * 信息验证成功：注册完成，退出当前页面（通过 finish 销毁当前 Activity）
  * 用户信息编辑界面
    * initView：初始化控件，进行变量和控件的绑定；
    * initData：初始化数据用于显示上一次编辑信息完成并保存后数据的显示；initEvent：监听事件
      * 保存按钮监听事件：读取控件中填写的信息，并进行本地存储 SharedPreferences（存储方式为键值对的形式）；
      * 时间显示监听事件：实现日期和时间框；
      * Spinner 监听事件实位置数据的存储。
  * 用户首页界面
    * initView：初始化控件，进行变量和控件的绑定；
    * initData：对控件进行数据赋值与更新，从本地存储中进行数据读取数据，并对更新于控件；
    * initEvent：监听事件，编辑按钮：实现界面跳转；退出登陆按钮：实现界面跳转，回到用户登陆界面。

#### （二）实现效果

<img src="C:\Users\yinjiaxuan\Desktop\1.png" alt="1" style="zoom:50%;" />

<img src="C:\Users\yinjiaxuan\Desktop\2.png" alt="2" style="zoom:50%;" />

<img src="C:\Users\yinjiaxuan\Desktop\3.png" alt="3" style="zoom: 67%;" />

<img src="C:\Users\yinjiaxuan\Desktop\4.png" alt="4" style="zoom:50%;" />
