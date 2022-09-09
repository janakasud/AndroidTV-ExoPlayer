# AndroidTV-ExoPlayer

This is an ExoPlayer app built on AndroidTV for testing different types of video streams.

The app has pre-loaded **DASH** video list , **UDP unicast** reciver, **UDP multicast** reciver, and UI for **URL input**.

The components of the app are described below.

<h3> 1. AndroidManifest </h1>

<h4> Declare a TV activity </h4>

```
<activity
            android:name=".MainActivity"
            android:banner="@drawable/app_icon"
            android:exported="true"
            android:icon="@drawable/app_icon"
            android:logo="@drawable/app_icon"
            android:screenOrientation="landscape">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LEANBACK_LAUNCHER" />
            </intent-filter>
        </activity>

```
<h4> Declare Leanback support </h4>

```
<uses-feature
        android:name="android.software.leanback"
        android:required="true" />
```

<h4> Declare touchscreen not required </h4>

```
<uses-feature
        android:name="android.hardware.touchscreen"
        android:required="false" />
```
<h3> 2. MainActivity </h3>

xml layout

```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main_browse_fragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    tools:deviceIds="tv"
    tools:ignore="MergeRootFrame" />
```

MainFragment replaces the layout container identified by the R.id.main_browse_fragment ID. 

```
class MainActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
    }
}

```

<h3> 3. MainFragment</h3>










