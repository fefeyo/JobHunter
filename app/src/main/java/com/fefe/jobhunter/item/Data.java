package com.fefe.jobhunter.item;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by USER on 2015/05/05.
 */

@Table(name = "company")
public class Data extends Model {

    /*　会社名　*/
    @Column(name = "name")
    public String name;

    /*　部署名　*/
    @Column(name = "position")
    public String position;

    /*　説明会　*/
    @Column(name = "guidance_month")
    public int guidance_month;

    @Column(name = "guidance_day")
    public int guidance_day;

    @Column(name = "guidance_time")
    public String guidance_time;

    @Column(name = "guidance_place")
    public String guidance_place;

    @Column(name = "used_guidance")
    public boolean used_guidance;

    /*　エントリーシート　*/
    @Column(name = "entryseat_start_month")
    public int entryseat_start_month;

    @Column(name = "entryseat_start_day")
    public int entryseat_start_day;

    @Column(name = "entryseat_end_month")
    public int entryseat_end_month;

    @Column(name = "entryseat_end_day")
    public int entryseat_end_day;


    @Column(name = "entryseat_system")
    public String entryseat_system;

    @Column(name = "entryseat_contains")
    public String entryseat_contains;

    @Column(name = "used_entryseat")
    public boolean used_entryseat;

    /*　履歴書　*/
    @Column(name = "personal_start_month")
    public int personal_start_month;

    @Column(name = "personal_start_day")
    public int personal_start_day;

    @Column(name = "personal_end_month")
    public int personal_end_month;

    @Column(name = "personal_end_day")
    public int personal_end_day;

    @Column(name = "personalseat_system")
    public String personalseat_system;

    @Column(name = "personalseat_format")
    public String personalseat_format;

    @Column(name = "used_personalseat")
    public boolean used_personalseat;

    /*　グループディスカッション　*/
    @Column(name ="groupdiscussion_month")
    public int groupdiscussion_month;

    @Column(name = "groupdiscussion_day")
    public int groupdiscussion_day;

    @Column(name = "groupdiscussion_time")
    public String groupdiscussion_time;

    @Column(name = "groupdiscussion_place")
    public String groupdiscussion_place;

    @Column(name = "groupdiscussion_clothes")
    public String groupdiscussion_clothes;

    @Column(name = "used_groupdiscussion")
    public boolean used_groupdiscussion;

    /*　１次面接　*/
    @Column(name ="interview_month_one")
    public int interview_month_one;

    @Column(name = "interview_day_one")
    public int interview_day_one;

    @Column(name = "interview_time_one")
    public String interview_time_one;

    @Column(name = "interview_place_one")
    public String interview_place_one;

    @Column(name = "interview_format_one")
    public String interview_format_one;

    @Column(name = "interview_person_student_one")
    public boolean interview_person_student_one;

    @Column(name = "interview_person_cto_one")
    public boolean interview_person_cto_one;

    @Column(name = "interview_person_ceo_one")
    public boolean interview_person_ceo_one;

    @Column(name = "interview_person_hr_one")
    public boolean interview_person_hr_one;

    @Column(name = "interview_clothes_one")
    public String interview_clothes_one;

    @Column(name = "used_interview_one")
    public boolean used_interview_one;

    /*　２次面接　*/
    @Column(name ="interview_month_twe")
    public int interview_month_twe;

    @Column(name = "interview_day_twe")
    public int interview_day_twe;

    @Column(name = "interview_time_twe")
    public String interview_time_twe;

    @Column(name = "interview_place_twe")
    public String interview_place_twe;

    @Column(name = "interview_format_twe")
    public String interview_format_twe;

    @Column(name = "interview_person_student_twe")
    public boolean interview_person_student_twe;

    @Column(name = "interview_person_cto_twe")
    public boolean interview_person_cto_twe;

    @Column(name = "interview_person_ceo_twe")
    public boolean interview_person_ceo_twe;

    @Column(name = "interview_person_hr_twe")
    public boolean interview_person_hr_twe;

    @Column(name = "interview_clothes_twe")
    public String interview_clothes_twe;

    @Column(name = "used_interview_twe")
    public boolean used_interview_twe;

    /*　３次面接　*/
    @Column(name ="interview_month_three")
    public int interview_month_three;

    @Column(name = "interview_day_three")
    public int interview_day_three;

    @Column(name = "interview_time_three")
    public String interview_time_three;

    @Column(name = "interview_place_three")
    public String interview_place_three;

    @Column(name = "interview_format_three")
    public String interview_format_three;

    @Column(name = "interview_person_student_three")
    public boolean interview_person_student_three;

    @Column(name = "interview_person_cto_three")
    public boolean interview_person_cto_three;

    @Column(name = "interview_person_ceo_three")
    public boolean interview_person_ceo_three;

    @Column(name = "interview_person_hr_three")
    public boolean interview_person_hr_three;

    @Column(name = "interview_clothes_three")
    public String interview_clothes_three;

    @Column(name = "used_interview_three")
    public boolean used_interview_three;

    /*　４次面接　*/
    @Column(name ="interview_month_four")
    public int interview_month_four;

    @Column(name = "interview_day_four")
    public int interview_day_four;

    @Column(name = "interview_time_four")
    public String interview_time_four;

    @Column(name = "interview_place_four")
    public String interview_place_four;

    @Column(name = "interview_format_four")
    public String interview_format_four;

    @Column(name = "interview_person_student_four")
    public boolean interview_person_student_four;

    @Column(name = "interview_person_cto_four")
    public boolean interview_person_cto_four;

    @Column(name = "interview_person_ceo_four")
    public boolean interview_person_ceo_four;

    @Column(name = "interview_person_hr_four")
    public boolean interview_person_hr_four;

    @Column(name = "interview_clothes_four")
    public String interview_clothes_four;

    @Column(name = "used_interview_four")
    public boolean used_interview_four;

    /*　５次面接　*/
    @Column(name ="interview_month_five")
    public int interview_month_five;

    @Column(name = "interview_day_five")
    public int interview_day_five;

    @Column(name = "interview_time_five")
    public String interview_time_five;

    @Column(name = "interview_place_")
    public String interview_place_five;

    @Column(name = "interview_format_five")
    public String interview_format_five;

    @Column(name = "interview_person_student_five")
    public boolean interview_person_student_five;

    @Column(name = "interview_person_cto_five")
    public boolean interview_person_cto_five;

    @Column(name = "interview_person_ceo_five")
    public boolean interview_person_ceo_five;

    @Column(name = "interview_person_hr_five")
    public boolean interview_person_hr_five;

    @Column(name = "interview_clothes_five")
    public String interview_clothes_five;

    @Column(name = "used_interview_five")
    public boolean used_interview_five;

    /*　最終面接　*/
    @Column(name = "interview_month_final")
    public int interview_month_final;

    @Column(name = "interview_day_final")
    public int interview_day_final;

    @Column(name ="interview_time_final")
    public String interview_time_final;

    @Column(name = "interview_place_final")
    public String interview_place_final;

    @Column(name = "interview_format_final")
    public String interview_format_final;

    @Column(name = "interview_person_student_final")
    public boolean interview_person_student_final;

    @Column(name = "interview_person_cto_final")
    public boolean interview_person_cto_final;

    @Column(name = "interview_person_ceo_final")
    public boolean interview_person_ceo_final;

    @Column(name = "interview_person_hr_final")
    public boolean interview_person_hr_final;

    @Column(name = "interview_clothes_final")
    public String interview_clothes_final;

    @Column(name = "used_interview_final")
    public boolean used_interview_final;

    /*　エントリー締め切り　*/
    @Column(name = "entryperiod_date_month")
    public int entryperiod_month;

    @Column(name = "entryperiod_date_day")
    public int entryperiod_day;

    @Column(name = "entryperiod_system")
    public String entryperiod_system;

    @Column(name = "entryperiod_format")
    public String entryperiod_format;

    @Column(name = "used_entryperiod")
    public boolean used_entryperiod;

    /*　登録日時　*/
    @Column(name = "time")
    public String time;

    /*　ラベルカラー　*/
    @Column(name = "color")
    public String color;

    public Data() {
        super();
    }

}

