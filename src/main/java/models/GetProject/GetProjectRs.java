package models.GetProject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProjectRs {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("result")
    @Expose
    public Result result;
}