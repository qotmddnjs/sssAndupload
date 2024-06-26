package com.koreait.exam.sssandupload.app.member.entity;


import com.koreait.exam.sssandupload.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
  @Column(unique = true)
  private String username;
  private String password;
  private String email;
  private String profileImg;
}