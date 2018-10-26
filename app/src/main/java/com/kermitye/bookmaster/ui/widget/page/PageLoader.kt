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
    protected lateinit var mChapterList: List<Chapter>
    //书本对象
    protected lateinit var mCollBook: SearchBook
    //监听器
    protected lateinit var mPageChangeListener: OnPageChangeListener

    private lateinit var mContext: Context
    //页面显示类
    private lateinit var mPageView: PageView
    //当前显示的页
    private lateinit var mCurPage: TxtPage
    //上一章的页面列表缓存
    private lateinit var mPrePageList: List<TxtPage>
    //当前章节的页面列表
    private lateinit var mCurPageList: List<TxtPage>
    //下一章的页面列表缓存
    private lateinit var mNextPageList: List<TxtPage>

    //绘制电池的画笔
    private lateinit var  mBatteryPaint: Paint
    //绘制提示的画笔
    private lateinit var mTipPaint: Paint
    //绘制标题的画笔
    private lateinit var mTitlePaint: Paint


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