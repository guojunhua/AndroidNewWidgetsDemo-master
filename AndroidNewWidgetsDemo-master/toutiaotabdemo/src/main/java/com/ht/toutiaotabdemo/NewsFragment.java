package com.ht.toutiaotabdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	private static final String ARG_POSITION = "position";

	private int position;
	private String appid = "15933";
	private String secret = "3d820a902d4b43f5bf81bb22e84ba744";
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RecyclerView mRecyclerView;
	private RecyclerView.LayoutManager mLayoutManager;
	private static final int SPAN_COUNT = 2;
	private MyStaggeredViewAdapter mStaggeredAdapter;
	private List<HuTuBean> mData;
	int i = 1;

	public static NewsFragment newInstance(int position) {
		NewsFragment f = new NewsFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
		Log.e("---position---",position+"--");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.newsfragment,null);
		mSwipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.id_swiperefreshlayout));
		mRecyclerView = ((RecyclerView) view.findViewById(R.id.id_recyclerview));
		mLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);
		mData = new ArrayList<HuTuBean>();
		mStaggeredAdapter = new MyStaggeredViewAdapter(getActivity());
		// 刷新时，指示器旋转后变化的颜色
		mSwipeRefreshLayout.setColorSchemeResources(R.color.google_blue,
				R.color.google_green,
				R.color.google_red,
				R.color.google_yellow);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		final AsyncHttpResponseHandler resHandler = new AsyncHttpResponseHandler() {
			public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable e) {
				//做一些异常处理
				e.printStackTrace();
			}

			public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
				try {
					String json = new String(responseBody, "utf-8");
					Log.e("---jun---", json);
					mData = parserJson(json);
					mStaggeredAdapter.setData(mData);
					mStaggeredAdapter.notifyDataSetChanged();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		};

		new ShowApiRequest("http://route.showapi.com/819-1", appid, secret)
				.setResponseHandler(resHandler)
				.addTextPara("type", ""+(34+position))
				.addTextPara("num", "50")
				.addTextPara("page", "1")
				.post();
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setAdapter(mStaggeredAdapter);
		return view;
	}
	@Override
	public void onRefresh() {
		// 刷新时模拟数据的变化
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mSwipeRefreshLayout.setRefreshing(false);
				final AsyncHttpResponseHandler resHandler = new AsyncHttpResponseHandler() {
					public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable e) {
						//做一些异常处理
						e.printStackTrace();
					}

					public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
						try {
							String json = new String(responseBody, "utf-8");
							Log.e("---jun---", json);
							mData = parserJson(json);
							mStaggeredAdapter.setData(mData);
							mStaggeredAdapter.notifyDataSetChanged();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				};
				new ShowApiRequest("http://route.showapi.com/819-1", appid, secret)
						.setResponseHandler(resHandler)
                        .addTextPara("type", ""+(34+position))
						.addTextPara("num", "50")
						.addTextPara("page", "" + i)
						.post();
			}
		}, 1000);
		i++;
	}
	public List<HuTuBean> parserJson(String json) {
		List<HuTuBean> data = null;
		try {
			data = new ArrayList<HuTuBean>();
			JSONObject object = new JSONObject(json).getJSONObject("showapi_res_body");
			for (int i = 0; i < 50; i++) {
				JSONObject o = object.getJSONObject("" + i);
				HuTuBean huTuBean = new HuTuBean();
				huTuBean.setThumb(o.getString("thumb"));
				huTuBean.setTitle(o.getString("title"));
				huTuBean.setUrl(o.getString("url"));
				data.add(huTuBean);
			}
			Log.e("---haha---", data.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
