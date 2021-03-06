/*
 * Copyright 2016 Rohit Sharma (skyrohithigh)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Created by rohit on 7/17/16.
 */
package rohit.bitshifters.com.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.bitshifters.rohit.jokeviewerlibrary.JokeViewer;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Context mContext;
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = root.getContext();
        mProgressBar = (ProgressBar) root.findViewById(R.id.joke_loading_spinner);

        //Setting up button
        Button button = (Button)  root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                new RetrieveJokeTask(new RetrieveJokeTask.Listener() {
                    @Override
                    public void onJokeLoaded(String joke) {
                        mProgressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(mContext, JokeViewer.class);
                        intent.putExtra(JokeViewer.JOKE_EXTRA, joke);
                        startActivity(intent);
                    }
                }).execute();
            }
        });


        return root;
    }
}
