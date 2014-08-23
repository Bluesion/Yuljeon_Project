package com.woncheol.yuljeon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
 
public class MyAlertDialogFragment extends DialogFragment {

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("알레르기 정보")
                .setMessage("① 난류 (달걀)\n② 우유\n③ 메밀\n④ 땅콩\n⑤ 대두\n⑥ 밀\n⑦ 고등어\n⑧ 게\n⑨ 새우\n⑩ 돼지고기\n⑪ 복숭아\n⑫ 토마토\n⑬ 아황산염")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
       .create();
    }
}