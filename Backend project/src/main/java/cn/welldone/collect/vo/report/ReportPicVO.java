package cn.welldone.collect.vo.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPicVO {
List<String> image_path;
int n_clusters;
}
