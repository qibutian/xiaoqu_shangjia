package com.srx.communitybutler;

import net.duohuo.dhroid.Const;
import net.duohuo.dhroid.adapter.ValueFix;
import net.duohuo.dhroid.dialog.IDialog;
import net.duohuo.dhroid.ioc.Instance.InstanceScope;
import net.duohuo.dhroid.ioc.IocContainer;
import net.duohuo.dhroid.net.GlobalCodeHandler;
import net.duohuo.dhroid.net.GlobalParams;
import net.duohuo.dhroid.net.cache.DaoHelper;
import net.duohuo.dhroid.util.UserLocation;
import android.app.Application;
import android.content.Context;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class ShopApplication extends Application implements
		Thread.UncaughtExceptionHandler {

	private static ShopApplication instance;

	public IDialog dialoger;

	public static ImageLoaderConfiguration imageconfig;

	public static ShopApplication getInstance() {
		return instance;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// Thread.setDefaultUncaughtExceptionHandler(this);
		instance = this;
		Const.netadapter_page_no = "currentpage";
		Const.response_total = "totalcount";
		Const.response_data = "data";
		Const.netadapter_step_default = 10;
		Const.DATABASE_VERSION = 23;
		Const.response_result_status = "1";
		Const.netadapter_no_more = "";
		// Const.postType = 2;
		IocContainer.getShare().initApplication(this);
		IocContainer.getShare().bind(ShopValueFix.class).to(ValueFix.class)
				.scope(InstanceScope.SCOPE_SINGLETON);
//		IocContainer.getShare().bind(NomalDialog.class).to(IDialog.class)
//				.scope(InstanceScope.SCOPE_SINGLETON);
//		IocContainer.getShare().bind(DaoHelper.class)
//				.to(OrmLiteSqliteOpenHelper.class)
//				.scope(InstanceScope.SCOPE_SINGLETON);
		IocContainer.getShare().bind(KmlGlobalCodeHandler.class)

		.to(GlobalCodeHandler.class).scope(InstanceScope.SCOPE_SINGLETON);
		dialoger = IocContainer.getShare().get(IDialog.class);
		// CrashHandler.getInstance().init();

		imageconfig = new ImageLoaderConfiguration.Builder(this)
				.memoryCacheExtraOptions(400, 400)
				// default = device screen dimensions
				.diskCacheExtraOptions(400, 400, null)
				.threadPoolSize(5)
				// default Thread.NORM_PRIORITY - 1
				.threadPriority(Thread.NORM_PRIORITY)
				// default FIFO
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024)
				.memoryCacheSizePercentage(13)
				// default
				.diskCache(
						new UnlimitedDiscCache(StorageUtils.getCacheDirectory(
								this, true)))
				// default
				.diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
				// default
				.imageDownloader(new BaseImageDownloader(this))
				// default
				.imageDecoder(new BaseImageDecoder(false))
				// default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.build();
		ImageLoader.getInstance().init(imageconfig);

		UserLocation.getInstance().init(this);
		
		
		GlobalParams globalParams = IocContainer.getShare().get(
				GlobalParams.class);

//		ShopPerference per = IocContainer.getShare().get(
//				ShopPerference.class);
//		per.load();
//		if (!TextUtils.isEmpty(per.schoolId)) {
//			globalParams.setGlobalParam("schoolid", per.schoolId);
//		}
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// System.out.println("濂旀簝.................");
		// Intent intent = new Intent(this, SplashActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// startActivity(intent);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
