/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.dto;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class SubRedditDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sub_id;
    @NotBlank(message = "Community name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    private List<PostDTO> posts;
    private Instant createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDTO userDTO;
    
}
