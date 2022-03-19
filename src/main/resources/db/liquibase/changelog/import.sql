/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  kwamaGithub
 * Created: 16 mars 2022
 */

/**

**/
INSERT INTO manage.parameters(code, label) 
values
('LISTING_STATE','listing possible states'),
('DEFAULT_TIER_LIMIT','Default Number of published listings a dealer can have online')
ON CONFLICT (code) DO UPDATE 
SET label= EXCLUDED.label;

/**

**/
INSERT INTO manage.values(id, label,parameter_code,value)
values 
('DF','Draft','LISTING_STATE','DFT'),
('PBD','Published','LISTING_STATE','PBHD'),
('TL','Default Tier limit','DEFAULT_TIER_LIMIT','5')
ON CONFLICT (id) DO UPDATE 
SET label= EXCLUDED.label,
parameter_code=EXCLUDED.parameter_code,
value=EXCLUDED.value;


/**

**/
INSERT INTO manage.dealer(id, name,tier_limit)
values 
('HDAI','Hyundai',NULL),
('HD','Honda',10),
('GC','GMC',0),
('II','INFINITI',20),
('JR','JAGUAR',5),
('JP','Jeep',2),
('NN','NISSAN',NULL)
ON CONFLICT (id) DO UPDATE 
SET name=EXCLUDED.name,
tier_limit=EXCLUDED.tier_limit;
