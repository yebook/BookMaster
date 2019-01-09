package com.kermitye.bookmaster.ui.widget.page

import android.content.Context
import android.graphics.Paint
import android.text.TextPaint
import com.kermitye.bookmaster.model.bean.Chapter
import com.kermitye.bookmaster.model.bean.SearchBook
import io.reactivex.disposables.Disposable

/**
 * Created by kermitye on 2018/10/25 16:50
 */
abstract class PageLoader {
    private val TAG: String = "PageLoader"

    companion object {
        // 当前页面的状态
        val STATUS_LOADING = 1         // 正在加载
        val STATUS_FINISH = 2          // 加载完成
        val STATUS_ERROR = 3           // 加载错误 (一般是网络加载情况)
        val STATUS_EMPTY = 4           // 空数据
        val STATUS_PARING = 5          // 正在解析 (装载本地数据)
        val STATUS_PARSE_ERROR = 6     // 本地文件解析错误(暂未被使用)
        val STATUS_CATEGORY_EMPTY = 7  // 获取到的目录为空
        // 默认的显示参数配置
        private val DEFAULT_MARGIN_HEIGHT = 28
        private val DEFAULT_MARGIN_WIDTH = 15
        private val DEFAULT_TIP_SIZE = 12
        private val EXTRA_TITLE_SIZE = 4
    }

    //当前章节列表
    protected var mChapterList: List<Chapter>? = null
    //书本对象
    protected var mCollBook: SearchBook? = null
    //监听器
    protected var mPageChangeListener: OnPageChangeListener? = null

    private var mContext: Context? = null
    //页面显示类
    private var mPageView: PageView? = null
    //当前显示的页
    private var mCurPage: TxtPage? = null
    //上一章的页面列表缓存
    private var mPrePageList: List<TxtPage>? = null
    //当前章节的页面列表
    private var mCurPageList: List<TxtPage>? = null
    //下一章的页面列表缓存
    private var mNextPageList: List<TxtPage>? = null

    //绘制电池的画笔
    private var  mBatteryPaint: Paint? = null
    //绘制提示的画笔
    private var mTipPaint: Paint? = null
    //绘制标题的画笔
    private var mTitlePaint: Paint? = null
    //绘制背景颜色的画笔（用来擦除需要重绘的部分）
    private var mBgPaint: Paint? = null
    //绘制小说内容的画笔
    private var mTextPaint: TextPaint? = null
    // 阅读器的配置选项
//    private lateinit var mSettingManager: ReadSettingManager
    // 被遮盖的页，或者认为被取消的页
    private var mCancelPage: TxtPage? = null
    // 存储阅读记录类
//    private lateinit var mBookRecord: BookRecordBean

    private var mPreLoadDisp: Disposable? = null

    /*************params****************/
    //当前状态
    private var mStatus: Int = 0
    //判断章节列表是否加载完成
    protected var isChapterListPrepare = false

    //是否打开过章节
    private var isChapterOpen = false
    private var isFirstOpen = true
    private var isClose = false
    //页面的翻页效果模式
    private var mPageMode: PageMode? = null
    //加载器的颜色主题
//    private var mPageStyle: PageStyle? = null
    //当前是否是夜间模式
    private var isNightMode = false
    //书籍绘制区域的宽高
    private var mVisibleWidth = 0
    private var mVisibleHeight = 0
    //应用的宽高
    private var mDisplayWidth = 0
    private var mDisplayHeiht = 0
    //间距
    private var mMarginWidth = DEFAULT_MARGIN_WIDTH
    private var mMarginHeight = DEFAULT_MARGIN_HEIGHT
    //字体的颜色
    private var mTextColor: Int? = null
    //标题的大小
    private var mTitleSize = EXTRA_TITLE_SIZE
    //字体的大小
    private var mTextSize = 0
    //行间距
    private var mTextInterval = 0
    //标题的行间距
    private var mTitleInterval = 0
    //段落距离(基于行间距的额外距离)
    private var mTextPara = 0
    private var mTitlePara = 0
    //电池百分比
    private var mBatteryLevel = 0
    //当前页面的背景
    private var mBgColor: Int? = null

    //当前章
    protected var mCurChapterPos = 0
    //上一章的记录
    private var mLastChapterPos = 0

    /****************init params*********************/




    interface OnPageChangeListener {
        /**
         * 作用：章节切换的时候进行回调
         *
         * @param pos:切换章节的序号
         */
        fun onChapterChange(pos: Int)

        /**
         * 作用：请求加载章节内容
         *
         * @param requestChapters:需要下载的章节列表
         */
        fun requestChapters(requestChapters: List<Chapter>)

        /**
         * 作用：章节目录加载完成时候回调
         *
         * @param chapters：返回章节目录
         */
        fun onCategoryFinish(chapters: List<Chapter>)

        /**
         * 作用：章节页码数量改变之后的回调。==> 字体大小的调整，或者是否关闭虚拟按钮功能都会改变页面的数量。
         *
         * @param count:页面的数量
         */
        fun onPageCountChange(count: Int)

        /**
         * 作用：当页面改变的时候回调
         *
         * @param pos:当前的页面的序号
         */
        fun onPageChange(pos: Int)
    }
}