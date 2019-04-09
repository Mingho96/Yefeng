package com.example.administrator.yefeng.page.homepage.contract;

public interface HomeContract {
    interface View{
        void showDialog();
        void hideDialog();
    }
    interface ListBiz{
        void Loading(LoadListener listener);
    }

    interface LoadListener{
        void Success();
        void Failed();
    }

}
