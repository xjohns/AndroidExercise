package com.example.xiong.fragmentbestpractice;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiong on 2016/12/14.
 */

public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private Boolean isTwoPane;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newsList = getNews();//初始化新闻数据
        adapter = new NewsAdapter(context, R.layout.news_item, newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_listView);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout)!= null){
            isTwoPane = true; // 可以找到news_content_layout布局时，为双页模式
        }else {
            isTwoPane = false;//找不到news_content_layout布局时，为单页模式
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        if (isTwoPane){
            // 如果是双页模式，则刷新NewsContentFragment中的内容
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(), news.getContent());
        }else {
            // 如果是单页模式，则直接启动NewsContentActivity
            NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("消费促进：从线上到线下");
        news1.setContent("线下消费在国民经济中的作用是基础性的，促进线下消费是消费促进的基础工作。" +
                "线上消费对线下消费产生了迭代冲击和转型升级的双重效果，线下消费日益呈现互联网化的趋势。" +
                "互联网平台拓展线下业务和线下商业机构互联网化发展可以有合集，一定程度上，“双12”是消费促进中线上线下融合的有益尝试。" +
                "当然，不管是“双11”还是“双12”都反映了我国消费的潜力以及某种抑制，构建消费促进的长效机制任重而道远。");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("谷歌：没有整合Chrome OS和Android必要");
        news2.setContent("北京时间12月14日消息，早前传闻称谷歌Chrome OS和Android将会逐步融合，打造一款代号为Andromeda的操作系统。" +
                "不过现在谷歌高管Hiroshi Lockheimer在接受All About Android播客采访时表示公司没有整合Android和Chrome的必要。" +
                "Hiroshi Lockheimer表示，“两套系统都非常成功。我们只想要确保双方能够相互受益，这就是我们带来软件的原因...Android下的Google Play进入Chrome OS，然后通过更新机制又从Chrome OS回到Nougat（Android 7.0）。");
        newsList.add(news2);
        return newsList;
    }

}
