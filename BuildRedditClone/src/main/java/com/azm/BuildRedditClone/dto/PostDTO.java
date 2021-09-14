/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import java.time.Instant;

/**
 *
 * @author azm
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String post_id;
    @NotBlank(message = "Post name cannot be empty or null")
    private String postName;
    @Nullable
    private String url;
    @Nullable
    @Lob
    private String description;
    private Integer voteCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private UserDTO userDTO;
    private Instant createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_id",referencedColumnName = "sub_id")
    private SubRedditDTO subRedditDTO;
      
    
}
