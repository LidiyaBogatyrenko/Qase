package models.CreateTestCase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTestCaseRs {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("result")
    @Expose
    public Result result;
}