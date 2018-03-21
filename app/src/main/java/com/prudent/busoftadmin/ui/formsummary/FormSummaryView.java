package com.prudent.busoftadmin.ui.formsummary;

import com.prudent.busoftadmin.data.api.model.FormSummarySaveData.response.FormSummarySaveResponse;
import com.prudent.busoftadmin.data.api.model.formsummary.response.FormSummaryResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryCrAccount.response.FormSummaryCrAccountResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.response.FormSummaryDrAccountResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryvoucher.response.FormSummaryVoucherResponse;
import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by AFF41 on 7/21/2017.
 */

public interface FormSummaryView extends View {

    void ShowProgress();

    void HideProgress();

    void Error(String Title, String Message);

    void ShowFormSummary(FormSummaryResponse formSummaryResponse);

    void ShowFormSummaryVoucher(FormSummaryVoucherResponse formSummaryVoucherResponse);

    void ShowFormSummaryDrAccount(FormSummaryDrAccountResponse formSummaryDrAccountResponse);

    void ShowFormSummaryCreditAccount(FormSummaryCrAccountResponse formSummaryCrAccountResponse);

    void SaveFormSummaryData(FormSummarySaveResponse summarySaveResponse);
}
