# Android积累 #
## broadcast
* BatteryReceiver ---电池电量广播监听
* BootBroadcastReceiver ---开机自启
* NetworkChangeReceiver ---网络连接状态变化广播接收器
* PowerStateChangeReceiver ---监听充电断点状态、断电后关机
## customView
* cardViewPager 滑动当前item变大其他变小
* pieChart 饼状图
* CircleActivity 圆形旋转菜单
* CircleShrinkActivity 圆形旋转缩放菜单
* RadarViewActivity 雷达view
# UtilsLib
## How to use UtilsLib
![image](https://github.com/AndroidZM233/ZMAndroidLog/blob/master/Pic/HowTo.png)
```
// init it in the function of onCreate in ur Application
Utils.init(context);
// or Your Application extends BaseApplication
MyApp extends BaseApplication
```
## utils
* data-BCDUtils ---bcd编码转换
* data-ByteUtils ---byte数据转换
* data-CRC16Utils ---CRC16编码转换
* data-IntegerUtils ---int转换
* data-PxSpDipDpUtils ---像素转换
* data-StringUtils ---String数据判断、转换
* AppUtils ---APP相关信息工具类
* ArrayUtils ---数组工具类
* CacheUtils ---缓存相关工具类
* CleanUtils ---清除相关工具类
* CloseUtils ---关闭相关工具类
* ConvertUtils ---转换相关工具类
* DateUtils ---日期操作
* DeviceUtils ---设备信息获取
* EncodeUtils ---编码解码相关工具类
* EncryptUtils ---加密解密相关的工具类
* FileUtils ---文件操作工具类
* FragmentUtils ---Fragment相关工具类
* GsonUtils ---gson工具类
* ImageUtils ---图片相关工具类
* IntentUtils ---意图相关工具类
* LogBlankjUtils ---Blankj大神log工具类
* LogToFileUtils ---log保存到SD卡(Android\data\包名\files\Log\logs.txt)
* LogUtils ---log工具类
* NetWorkUtils ---网络连接相关
* RegexUtils ---正则相关工具类验证字符串规则
* ScreenUtils ---屏幕相关工具类
* ServiceUtils ---服务相关工具类
* SnackbarUtils ---Snackbar相关工具类
* StatusBarUtil ---状态栏设置
* Utils ---Utils初始化相关
* ZipUtils ---压缩相关工具类
## base
* BaseActivity
* BaseApplication
* BaseFragment
## view
* CardViewPager 图片ViewPager
* DatePicker 日期选择
![image](https://github.com/AndroidZM233/ZMAndroidLog/blob/master/Pic/datePicker.png)
* LinkedViewPager ViewPager联动
* MovingImageView 图片移动view
* CircleMenuLayout 圆形旋转菜单
* CircleShrinkMenuLayout 圆形旋转缩放菜单
* RadarView 雷达view
* BatteryView 自定义电池view
* ClearEditText 自带清除按钮TextView
* MarqueeTextView 跑马灯效果的textview